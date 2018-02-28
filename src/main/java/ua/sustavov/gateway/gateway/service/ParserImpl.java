package ua.sustavov.gateway.gateway.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.springframework.stereotype.Service;
import ua.sustavov.gateway.gateway.entity.AuthTransaction;

import java.io.IOException;

@Service
public class ParserImpl implements Parser {

    private final String RESPONSE_CODE = "responseCode";
    private final String AUTH_CODE = "authCode";
    private final String AVS_RESULT_CODE = "avsResultCode";
    private final String CVV_RESULT_CODE = "cvvResultCode";
    private final String TRANS_ID = "transId";
    private final String RESULT_CODE = "resultCode";
    private final String DESCRIPTION = "description";
    private final String TEXT = "text";
    private final String CODE = "code";

    @Override
    public void parse(AuthTransaction authTransaction, String responseContent) {

        responseContent = responseContent.replace("\uFEFF", "");

        JsonFactory jsonFactory = new JsonFactory();
        try {
            JsonParser jsonParser = jsonFactory.createParser(responseContent);
            while (!jsonParser.isClosed()) {
                JsonToken jsonToken = jsonParser.nextToken();

                if (JsonToken.FIELD_NAME.equals(jsonToken)) {
                    String fieldName = jsonParser.getCurrentName();
                    jsonToken = jsonParser.nextToken();

                    if (RESPONSE_CODE.equals(fieldName)) {
                        authTransaction.setResponseCode(jsonParser.getValueAsString());
                    } else if (AUTH_CODE.equals(fieldName)) {
                        authTransaction.setAuthCode(jsonParser.getValueAsString());
                    } else if (CVV_RESULT_CODE.equals(fieldName)) {
                        authTransaction.setCvvResultCode(jsonParser.getValueAsString());
                    } else if (TRANS_ID.equals(fieldName)) {
                        authTransaction.setTransId(jsonParser.getValueAsString());
                    } else if (RESULT_CODE.equals(fieldName)) {
                        authTransaction.setResultCode(jsonParser.getValueAsString());
                    } else if (AVS_RESULT_CODE.equals(fieldName)) {
                        authTransaction.setAvsResultCode(jsonParser.getValueAsString());
                    } else if (DESCRIPTION.equals(fieldName)) {
                        authTransaction.setDescription(jsonParser.getValueAsString());
                    } else if (TEXT.equals(fieldName)) {
                        authTransaction.setMessageText(jsonParser.getValueAsString());
                    } else if (CODE.equals(fieldName)) {
                        authTransaction.setMessageCode(jsonParser.getValueAsString());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
