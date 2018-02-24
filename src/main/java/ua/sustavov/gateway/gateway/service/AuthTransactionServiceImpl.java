package ua.sustavov.gateway.gateway.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sustavov.gateway.gateway.entity.AuthTransaction;
import ua.sustavov.gateway.gateway.repository.AuthTransactionRepository;

import javax.transaction.Transactional;

@Service
@Slf4j
public class AuthTransactionServiceImpl implements AuthTransactionService {

    private final AuthTransactionRepository authTransactionRepository;

    @Autowired
    public AuthTransactionServiceImpl(AuthTransactionRepository authTransactionRepository) {
        this.authTransactionRepository = authTransactionRepository;
    }

    @Override
    @Transactional
    public AuthTransaction saveEntity(AuthTransaction authTransaction) {
        log.info("Save transaction", authTransaction);
        return authTransactionRepository.saveAndFlush(authTransaction);
    }
}
