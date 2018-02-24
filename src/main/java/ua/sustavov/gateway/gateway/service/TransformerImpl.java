package ua.sustavov.gateway.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sustavov.gateway.gateway.entity.AuthTransaction;
import ua.sustavov.gateway.gateway.entity.Transaction;
import ua.sustavov.gateway.gateway.mapper.AuthTransToTransMapper;

@Service
public class TransformerImpl implements Transformer {

    private final AuthTransToTransMapper authTransToTransMapper;

    @Autowired
    public TransformerImpl(AuthTransToTransMapper authTransToTransMapper) {
        this.authTransToTransMapper = authTransToTransMapper;
    }

    @Override
    public AuthTransaction transform(Transaction transaction) {
        return authTransToTransMapper.transToAuthTrans(transaction);
    }
}
