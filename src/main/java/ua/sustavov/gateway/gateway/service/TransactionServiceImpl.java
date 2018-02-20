package ua.sustavov.gateway.gateway.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sustavov.gateway.gateway.entity.Transaction;
import ua.sustavov.gateway.gateway.repository.TransactionRepository;

import javax.transaction.Transactional;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public Transaction saveEntity(Transaction transaction) {
        log.info("Save transaction", transaction);
        return transactionRepository.save(transaction);
    }
}
