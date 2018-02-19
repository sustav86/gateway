package ua.sustavov.gateway.gateway.service;

import org.springframework.stereotype.Service;
import ua.sustavov.gateway.gateway.dto.AuthTransactionDto;
import ua.sustavov.gateway.gateway.util.JsonMapper;

@Service
public class GeneratorImpl implements Generator {
    @Override
    public String generate(AuthTransactionDto authTransactionDto, String json) {

        String result = JsonMapper.DtoToJson(authTransactionDto, json);

        return result;

    }
}
