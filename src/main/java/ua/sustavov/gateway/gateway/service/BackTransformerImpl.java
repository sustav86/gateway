package ua.sustavov.gateway.gateway.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ua.sustavov.gateway.gateway.entity.AuthTransaction;
import ua.sustavov.gateway.gateway.entity.Transaction;

@Service
public class BackTransformerImpl implements BackTransformer {
    @Override
    public void transform(Transaction transaction, AuthTransaction authTransaction) {
        BeanUtils.copyProperties(authTransaction, transaction);
    }
}
