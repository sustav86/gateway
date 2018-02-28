package ua.sustavov.gateway.gateway.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sustavov.gateway.gateway.entity.AuthTransaction;

@Service
public class GeneratorImpl implements Generator {

    private final static String CREATE_TRANSACTION_REQUEST = "createTransactionRequest";
    private final static String MERCHANT_AUTHENTICATION = "merchantAuthentication";
    private final static String TRANSACTION_REQUEST = "transactionRequest";
    private final static String PAYMENT = "payment";
    private final static String CREDIT_CARD = "creditCard";
    private final static String CUSTOMER = "customer";
    private final static String BILL_TO = "billTo";

    private final static String NAME = "name";
    private final static String TRANSACTION_KEY = "transactionKey";
    private final static String TRANSACTION_TYPE_KEY = "transactionType";
    private final static String TRANSACTION_TYPE_VALUE = "authCaptureTransaction";
    private final static String AMOUNT = "amount";
    private final static String CARD_NUMBER = "cardNumber";
    private final static String EXPIRATION_DATE = "expirationDate";
    private final static String CARD_CODE = "cardCode";
    private final static String PO_NUMBER = "poNumber";
    private final static String ID = "id";
    private final static String FIRST_NAME = "firstName";
    private final static String LAST_NAME = "lastName";
    private final static String ADDRESS = "address";
    private final static String CITY = "city";
    private final static String STATE = "state";
    private final static String ZIP = "zip";

    private final ObjectMapper objectMapper;

    @Autowired
    public GeneratorImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String generate(AuthTransaction authTransaction) {

        JsonNode rootNode = objectMapper.createObjectNode();

        JsonNode createTransactionRequest = objectMapper.createObjectNode();

        JsonNode merchantAuthentication = objectMapper.createObjectNode();
        ((ObjectNode) merchantAuthentication).put(NAME, authTransaction.getUserName());
        ((ObjectNode) merchantAuthentication).put(TRANSACTION_KEY, authTransaction.getApiKey());

        ((ObjectNode) createTransactionRequest).set(MERCHANT_AUTHENTICATION, merchantAuthentication);

        ((ObjectNode) rootNode).set(CREATE_TRANSACTION_REQUEST, createTransactionRequest);

        JsonNode transactionRequest = objectMapper.createObjectNode();
        ((ObjectNode) transactionRequest).put(TRANSACTION_TYPE_KEY, TRANSACTION_TYPE_VALUE);
        ((ObjectNode) transactionRequest).put(AMOUNT, authTransaction.getAmount());

        JsonNode payment = objectMapper.createObjectNode();
        JsonNode creditCard = objectMapper.createObjectNode();
        ((ObjectNode) creditCard).put(CARD_NUMBER, authTransaction.getCardNumber());
        ((ObjectNode) creditCard).put(EXPIRATION_DATE, authTransaction.getExpirationDate());
        ((ObjectNode) creditCard).put(CARD_CODE, authTransaction.getCardCode());
        ((ObjectNode) payment).set(CREDIT_CARD, creditCard);
        ((ObjectNode) transactionRequest).set(PAYMENT, payment);

        ((ObjectNode) transactionRequest).put(PO_NUMBER, authTransaction.getBillToPhoneNumber());

        JsonNode customer = objectMapper.createObjectNode();
        ((ObjectNode) customer).put(ID, authTransaction.getCustomerId());
        ((ObjectNode) transactionRequest).set(CUSTOMER, customer);

        JsonNode billTo = objectMapper.createObjectNode();
        ((ObjectNode) billTo).put(FIRST_NAME, authTransaction.getBillToFirstName());
        ((ObjectNode) billTo).put(LAST_NAME, authTransaction.getBillToLastName());
        ((ObjectNode) billTo).put(ADDRESS, authTransaction.getBillToAddress());
        ((ObjectNode) billTo).put(CITY, authTransaction.getBillToCity());
        ((ObjectNode) billTo).put(STATE, authTransaction.getBillToState());
        ((ObjectNode) billTo).put(ZIP, authTransaction.getBillToZip());
        ((ObjectNode) transactionRequest).set(BILL_TO, billTo);

        ((ObjectNode) createTransactionRequest).set(TRANSACTION_REQUEST, transactionRequest);

        String serialized = "";
        try {
            serialized = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return serialized;
    }
}
