package ua.sustavov.gateway.gateway.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ua.sustavov.gateway.gateway.entity.AuthTransaction;

import java.io.IOException;

public class AuthTransactionDeserializer extends StdDeserializer<AuthTransaction> {

    private final String RESPONSE_CODE = "responseCode";
    private final String AUTH_CODE = "authCode";
    private final String AVS_RESULT_CODE = "avsResultCode";
    private final String CVV_RESULT_CODE = "cvvResultCode";
    private final String TRANS_ID = "transId";
    private final String RESULT_CODE = "resultCode";
    private final String DESCRIPTION = "description";
    private final String TEXT = "text";
    private final String CODE = "code";

    public AuthTransactionDeserializer() {
        this(null);
    }

    public AuthTransactionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public AuthTransaction deserialize(JsonParser jsonParser,
                                       DeserializationContext deserializationContext) throws IOException {

        AuthTransaction responseWrapper = new AuthTransaction();

        while (!jsonParser.isClosed()) {
            JsonToken jsonToken = jsonParser.nextToken();

            if (JsonToken.FIELD_NAME.equals(jsonToken)) {
                String fieldName = jsonParser.getCurrentName();
                jsonToken = jsonParser.nextToken();

                if (RESPONSE_CODE.equals(fieldName)) {
                    responseWrapper.setResponseCode(jsonParser.getValueAsString());
                } else if (AUTH_CODE.equals(fieldName)) {
                    responseWrapper.setAuthCode(jsonParser.getValueAsString());
                } else if (CVV_RESULT_CODE.equals(fieldName)) {
                    responseWrapper.setCvvResultCode(jsonParser.getValueAsString());
                } else if (TRANS_ID.equals(fieldName)) {
                    responseWrapper.setTransId(jsonParser.getValueAsString());
                } else if (RESULT_CODE.equals(fieldName)) {
                    responseWrapper.setResultCode(jsonParser.getValueAsString());
                } else if (AVS_RESULT_CODE.equals(fieldName)) {
                    responseWrapper.setAvsResultCode(jsonParser.getValueAsString());
                } else if (DESCRIPTION.equals(fieldName)) {
                    responseWrapper.setDescription(jsonParser.getValueAsString());
                } else if (TEXT.equals(fieldName)) {
                    responseWrapper.setMessageText(jsonParser.getValueAsString());
                } else if (CODE.equals(fieldName)) {
                    responseWrapper.setMessageCode(jsonParser.getValueAsString());
                }
            }
        }


        return responseWrapper;
    }
}
