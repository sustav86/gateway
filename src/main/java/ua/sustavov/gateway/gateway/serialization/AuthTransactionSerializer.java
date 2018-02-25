package ua.sustavov.gateway.gateway.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ua.sustavov.gateway.gateway.entity.AuthTransaction;
import ua.sustavov.gateway.gateway.util.CurrentDate;

import java.io.IOException;

public class AuthTransactionSerializer extends StdSerializer<AuthTransaction> {

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
    private final static String TRANSACTION_TYPE_VALUE = "authOnlyTransaction";
    private final static String AMOUNT = "amount";
    private final static String CARD_NUMBER_KEY = "cardNumber";
    private final static String CARD_NUMBER_VALUE = "5424000000000015";
    private final static String EXPIRATION_DATE = "expirationDate";
    private final static String CARD_CODE_KEY = "cardCode";
    private final static String CARD_CODE_VALUE = "999";
    private final static String PO_NUMBER = "poNumber";
    private final static String ID = "id";
    private final static String FIRST_NAME = "firstName";
    private final static String LAST_NAME = "lastName";
    private final static String ADDRESS = "address";
    private final static String CITY = "city";
    private final static String STATE = "state";
    private final static String ZIP = "zip";

    public AuthTransactionSerializer() {
        this(null);
    }

    public AuthTransactionSerializer(Class<AuthTransaction> t) {
        super(t);
    }

    @Override
    public void serialize(AuthTransaction authTransaction,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {

//        root
        jsonGenerator.writeStartObject();

        jsonGenerator.writeObjectFieldStart(CREATE_TRANSACTION_REQUEST);

        jsonGenerator.writeObjectFieldStart(MERCHANT_AUTHENTICATION);
        jsonGenerator.writeStringField(NAME, authTransaction.getUserName());
        jsonGenerator.writeStringField(TRANSACTION_KEY, authTransaction.getApiKey());
//        end_MERCHANT_AUTHENTICATION
        jsonGenerator.writeEndObject();

//        jsonGenerator.writeStringField("refId", "1234");

        jsonGenerator.writeObjectFieldStart(TRANSACTION_REQUEST);
        jsonGenerator.writeStringField(TRANSACTION_TYPE_KEY, TRANSACTION_TYPE_VALUE);
        jsonGenerator.writeStringField(AMOUNT, authTransaction.getAmount());
        jsonGenerator.writeObjectFieldStart(PAYMENT);
        jsonGenerator.writeObjectFieldStart(CREDIT_CARD);
        jsonGenerator.writeStringField(CARD_NUMBER_KEY, CARD_NUMBER_VALUE);
        jsonGenerator.writeStringField(EXPIRATION_DATE, CurrentDate.getCurrentDate());
        jsonGenerator.writeStringField(CARD_CODE_KEY, CARD_CODE_VALUE);
//        end_CREDIT_CARD
        jsonGenerator.writeEndObject();
//        end_PAYMENT
        jsonGenerator.writeEndObject();
        jsonGenerator.writeStringField(PO_NUMBER, authTransaction.getBillToPhoneNumber());

        jsonGenerator.writeObjectFieldStart(CUSTOMER);
        jsonGenerator.writeStringField(ID, authTransaction.getCustomerId());
//        end_CUSTOMER
        jsonGenerator.writeEndObject();

        jsonGenerator.writeObjectFieldStart(BILL_TO);
        jsonGenerator.writeStringField(FIRST_NAME, authTransaction.getBillToFirstName());
        jsonGenerator.writeStringField(LAST_NAME, authTransaction.getBillToLastName());
        jsonGenerator.writeStringField(ADDRESS, authTransaction.getBillToAddress());
        jsonGenerator.writeStringField(CITY, authTransaction.getBillToCity());
        jsonGenerator.writeStringField(STATE, authTransaction.getBillToState());
        jsonGenerator.writeStringField(ZIP, authTransaction.getBillToZip());
//        end_BILL_TO
        jsonGenerator.writeEndObject();
//        end_TRANSACTION_REQUEST
        jsonGenerator.writeEndObject();
//        end_CREATE_TRANSACTION_REQUEST
        jsonGenerator.writeEndObject();
//        end_root
        jsonGenerator.writeEndObject();

    }

}
