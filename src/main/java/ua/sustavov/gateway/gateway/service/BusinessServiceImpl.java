package ua.sustavov.gateway.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sustavov.gateway.gateway.dto.TransactionDto;
import ua.sustavov.gateway.gateway.entity.AuthTransaction;
import ua.sustavov.gateway.gateway.entity.Transaction;
import ua.sustavov.gateway.gateway.mapper.TransactionMapper;

@Service
public class BusinessServiceImpl implements BusinessService {

    private final TransactionMapper transactionMapper;
    private final Transformer transformer;
    private final Generator generator;
    private final Connector connector;
    private final Parser parser;
    private final BackTransformer backTransformer;
    private final TransactionService transactionService;
    private final AuthTransactionService authTransactionService;

    @Autowired
    public BusinessServiceImpl(TransactionMapper transactionMapper,
                               Transformer transformer,
                               Generator generator,
                               Connector connector,
                               Parser parser,
                               BackTransformer backTransformer,
                               TransactionService transactionService,
                               AuthTransactionService authTransactionService) {
        this.transactionMapper = transactionMapper;
        this.transformer = transformer;
        this.generator = generator;
        this.connector = connector;
        this.parser = parser;
        this.backTransformer = backTransformer;
        this.transactionService = transactionService;
        this.authTransactionService = authTransactionService;
    }

    @Override
    public TransactionDto performTransaction(TransactionDto transactionDto) {

        Transaction transaction = transactionMapper.toEntity(transactionDto);
//        Transformer
        AuthTransaction authTransaction = transformer.transform(transaction);
//        Generator
        String request = generator.generate(authTransaction);
//        Connector
        String response = connector.sendData(request);
//        Parser
        parser.parse(authTransaction, response);
//        BackTransformer
        backTransformer.transform(transaction, authTransaction);

        transactionDto = transactionMapper.toDto(transactionService.saveEntity(transaction));
        if (transactionDto != null) {
            authTransaction.setTransaction(transaction);
            authTransactionService.saveEntity(authTransaction);
        }

        return transactionDto;
    }
}
