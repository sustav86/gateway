package ua.sustavov.gateway.gateway.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sustavov.gateway.gateway.entity.AuthTransaction;

import java.io.IOException;

@Service
public class ParserImpl implements Parser {

    private final ObjectMapper objectMapper;

    @Autowired
    public ParserImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void parse(AuthTransaction authTransaction, String responseContent) {

        responseContent = responseContent.replace("\uFEFF", "");
        AuthTransaction responseValue = null;
        try {
            responseValue = objectMapper.readValue(responseContent, AuthTransaction.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BeanUtils.copyProperties(responseValue, authTransaction,
                "userName", "apiKey", "amount",
                "customerId", "billToFirstName", "billToLastName",
                "billToAddress", "billToCity", "billToState", "billToZip", "billToPhoneNumber");

    }
}
