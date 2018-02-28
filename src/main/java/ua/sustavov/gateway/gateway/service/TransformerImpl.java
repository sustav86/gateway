package ua.sustavov.gateway.gateway.service;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sustavov.gateway.gateway.entity.AuthTransaction;
import ua.sustavov.gateway.gateway.entity.Transaction;

@Service
public class TransformerImpl implements Transformer {

    private final DozerBeanMapper dozerBeanMapper;

    @Autowired
    public TransformerImpl(DozerBeanMapper dozerBeanMapper) {
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @Override
    public AuthTransaction transform(Transaction transaction) {
        return dozerBeanMapper.map(transaction, AuthTransaction.class);
    }
}
