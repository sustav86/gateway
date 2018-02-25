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
    private final static String TRANSACTION_TYPE = "authOnlyTransaction";
    private final static String PAYMENT = "payment";
    private final static String CREDIT_CARD = "creditCard";
    private final static String CUSTOMER = "customer";
    private final static String BILL_TO = "billTo";

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
        jsonGenerator.writeStringField("name", authTransaction.getUserName());
        jsonGenerator.writeStringField("transactionKey", authTransaction.getApiKey());
//        end_MERCHANT_AUTHENTICATION
        jsonGenerator.writeEndObject();

        jsonGenerator.writeStringField("refId", "1234");

        jsonGenerator.writeObjectFieldStart(TRANSACTION_REQUEST);
        jsonGenerator.writeStringField("transactionType", TRANSACTION_TYPE);
        jsonGenerator.writeStringField("amount", authTransaction.getAmount());
        jsonGenerator.writeObjectFieldStart(PAYMENT);
        jsonGenerator.writeObjectFieldStart(CREDIT_CARD);
        jsonGenerator.writeStringField("cardNumber", "5424000000000015");
        jsonGenerator.writeStringField("expirationDate", CurrentDate.getCurrentDate());
        jsonGenerator.writeStringField("cardCode", "999");
//        end_CREDIT_CARD
        jsonGenerator.writeEndObject();
//        end_PAYMENT
        jsonGenerator.writeEndObject();
        jsonGenerator.writeStringField("poNumber", authTransaction.getBillToPhoneNumber());

        jsonGenerator.writeObjectFieldStart(CUSTOMER);
        jsonGenerator.writeStringField("id", authTransaction.getCustomerId());
//        end_CUSTOMER
        jsonGenerator.writeEndObject();

        jsonGenerator.writeObjectFieldStart(BILL_TO);
        jsonGenerator.writeStringField("firstName", authTransaction.getBillToFirstName());
        jsonGenerator.writeStringField("lastName", authTransaction.getBillToLastName());
        jsonGenerator.writeStringField("address", authTransaction.getBillToAddress());
        jsonGenerator.writeStringField("city", authTransaction.getBillToCity());
        jsonGenerator.writeStringField("state", authTransaction.getBillToState());
        jsonGenerator.writeStringField("zip", authTransaction.getBillToZip());
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
