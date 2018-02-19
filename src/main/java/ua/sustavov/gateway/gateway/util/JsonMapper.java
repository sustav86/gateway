package ua.sustavov.gateway.gateway.util;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import ua.sustavov.gateway.gateway.dto.AuthTransactionDto;
import ua.sustavov.gateway.gateway.dto.TransactionDto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonMapper {

    private static ObjectMapper mapper = new ObjectMapper();
    private final static String CREATE_TRANSACTION_REQUEST = "createTransactionRequest";
    private final static String MERCHANT_AUTHENTICATION = "merchantAuthentication";
    private final static String TRANSACTION_REQUEST = "transactionRequest";
    private final static String PAYMENT = "payment";
    private final static String CREDIT_CARD = "creditCard";
    private final static String BILL_TO = "billTo";
    private final static String CUSTOMER = "customer";

    private final static String TRANSACTION_RESPONSE = "transactionResponse";
    private final static String MESSAGES = "messages";
    private final static String MESSAGE = "message";

    public static TransactionDto MapToDto(String json) {

        Map<String, String> result = new HashMap<>();
        Map<String, String> bufferMap = new HashMap<>();
        TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String, Object>>() {};
        JsonNode currentNode;
        TransactionDto transactionDto = null;

        try {
            currentNode = mapper.readTree(json).path(CREATE_TRANSACTION_REQUEST).path(MERCHANT_AUTHENTICATION);
            bufferMap = mapper.readValue(currentNode, typeReference);
            result.putAll(bufferMap);

            currentNode = mapper.readTree(json).path(CREATE_TRANSACTION_REQUEST).path(TRANSACTION_REQUEST);
            bufferMap = mapper.readValue(currentNode, typeReference);
            result.putAll(bufferMap);

            currentNode = mapper.readTree(json).path(CREATE_TRANSACTION_REQUEST).path(TRANSACTION_REQUEST).path(BILL_TO);
            bufferMap = mapper.readValue(currentNode, typeReference);
            result.putAll(bufferMap);

            currentNode = mapper.readTree(json).path(CREATE_TRANSACTION_REQUEST).path(TRANSACTION_REQUEST).path(CUSTOMER);
            bufferMap = mapper.readValue(currentNode, typeReference);
            result.putAll(bufferMap);

            currentNode = mapper.readTree(json).path(CREATE_TRANSACTION_REQUEST).path(TRANSACTION_REQUEST).path(PAYMENT).path(CREDIT_CARD);
            bufferMap = mapper.readValue(currentNode, typeReference);
            result.putAll(bufferMap);

            transactionDto = mapper.convertValue(result, TransactionDto.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return transactionDto;
    }

    public static String DtoToJson(AuthTransactionDto authTransactionDto, String json) {

        Map<String, String> map = mapper.convertValue(authTransactionDto, Map.class);
        Map<String, String> jsonMap = new HashMap<>();
        JsonNode node;
        try {
            node = mapper.readTree(json);
            recursiveTree(node, jsonMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String entryKey = entry.getKey();
            String entryValue = entry.getValue();
            if (jsonMap.containsKey(entryKey) && entryValue != null) {
                String jsonValue = jsonMap.get(entryKey);
                json.replace(jsonValue, entryValue);
            }

        }

        return json;
    }

    public static void recursiveTree(JsonNode node, Map<String, String> map) {

        Iterator<Map.Entry<String, JsonNode>> fields = node.getFields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            if (entry.getValue().size() > 0) {
                recursiveTree(entry.getValue(), map);
            }else {
                map.put(entry.getKey(), entry.getValue().getTextValue());
            }
        }
    }

    public static AuthTransactionDto ResponseToDto(AuthTransactionDto authTransactionDto, String json) {

        json = json.replace("\uFEFF", "");
        Map<String, String> map = mapper.convertValue(authTransactionDto, Map.class);
        Map<String, String> bufferMap = new HashMap<>();
        TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String, Object>>() {};
//        TypeReference<List<Map<String, Object>>> typeReferenceList = new TypeReference<List<Map<String, Object>>>() {};
        TypeReference<List<String>> typeReferenceList = new TypeReference<List<String>>() {};
        JsonNode currentNode;
        AuthTransactionDto responseAuthTrans = null;

        try {
            currentNode = mapper.readTree(json).path(TRANSACTION_RESPONSE);
            bufferMap = mapper.readValue(currentNode, typeReference);
            map.putAll(bufferMap);

            //TODO
//            currentNode = mapper.readTree(json).path(TRANSACTION_RESPONSE).path(MESSAGES);
//            bufferMap = mapper.readValue(currentNode, typeReferenceList);
//            map.putAll(bufferMap);
//
//            currentNode = mapper.readTree(json).path(MESSAGES).path(MESSAGE);
//            bufferMap = mapper.readValue(currentNode, typeReferenceList);
//            map.putAll(bufferMap);

            responseAuthTrans = mapper.convertValue(map, AuthTransactionDto.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseAuthTrans;

    }
}
