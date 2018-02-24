package ua.sustavov.gateway.gateway.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sustavov.gateway.gateway.entity.Transaction;
import ua.sustavov.gateway.gateway.mapper.TransactionMapper;
import ua.sustavov.gateway.gateway.repository.TransactionRepository;

import javax.transaction.Transactional;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    @Transactional
    public Transaction saveEntity(Transaction transaction) {
        log.info("Save transaction", transaction);
        return transactionRepository.saveAndFlush(transaction);
    }

}
