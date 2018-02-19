package ua.sustavov.gateway.gateway.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ua.sustavov.gateway.gateway.dto.AuthTransactionDto;
import ua.sustavov.gateway.gateway.entity.AuthTransaction;

@Service
public class TransformerImpl implements Transformer {

    @Override
    public AuthTransaction transform(AuthTransactionDto authTransactionDto) {

        AuthTransaction authTransaction = new AuthTransaction();
        BeanUtils.copyProperties(authTransactionDto, authTransaction);

        return authTransaction;
    }

}
