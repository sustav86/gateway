package ua.sustavov.gateway.gateway.service;

import ua.sustavov.gateway.gateway.entity.Transaction;

public interface BusinessService {

    Transaction performTransaction(Transaction transaction);
}
