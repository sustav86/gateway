package ua.sustavov.gateway.gateway.service;

import ua.sustavov.gateway.gateway.dto.TransactionDto;

public interface BusinessService {

    TransactionDto performTransaction(TransactionDto transactionDto);
}
