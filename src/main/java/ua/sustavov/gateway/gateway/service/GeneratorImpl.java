package ua.sustavov.gateway.gateway.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sustavov.gateway.gateway.entity.AuthTransaction;

@Service
public class GeneratorImpl implements Generator {

    private final ObjectMapper objectMapper;

    @Autowired
    public GeneratorImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String generate(AuthTransaction authTransaction) {

        String serialized = "";
        try {
            serialized = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(authTransaction);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return serialized;
    }
}
