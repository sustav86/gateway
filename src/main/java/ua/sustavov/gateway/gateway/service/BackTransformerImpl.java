package ua.sustavov.gateway.gateway.service;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sustavov.gateway.gateway.entity.AuthTransaction;
import ua.sustavov.gateway.gateway.entity.Transaction;

@Service
public class BackTransformerImpl implements BackTransformer {

    private final DozerBeanMapper dozerBeanMapper;

    @Autowired
    public BackTransformerImpl(DozerBeanMapper dozerBeanMapper) {
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @Override
    public void transform(Transaction transaction, AuthTransaction authTransaction) {
        dozerBeanMapper.map(authTransaction, transaction);
    }
}
