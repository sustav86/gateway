package ua.sustavov.gateway.gateway.service;

import ua.sustavov.gateway.gateway.entity.AuthTransaction;
import ua.sustavov.gateway.gateway.entity.Transaction;

public interface BackTransformer {

    void transform(Transaction transaction, AuthTransaction authTransaction);

}