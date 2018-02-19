package ua.sustavov.gateway.gateway.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ua.sustavov.gateway.gateway.entity.AuthTransaction;

import java.io.IOException;

@Service
public class ParserImpl implements Parser {
    @Override
    public void parse(AuthTransaction authTransaction, String responseContent) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            authTransaction = mapper.readValue(responseContent, AuthTransaction.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
