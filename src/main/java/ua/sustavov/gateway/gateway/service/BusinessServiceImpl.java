package ua.sustavov.gateway.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sustavov.gateway.gateway.entity.AuthTransaction;
import ua.sustavov.gateway.gateway.entity.Transaction;

@Service
public class BusinessServiceImpl implements BusinessService {

    private final Transformer transformer;
    private final Generator generator;
    private final Connector connector;
    private final Parser parser;
    private final BackTransformer backTransformer;
    private final TransactionService transactionService;
    private final AuthTransactionService authTransactionService;

    @Autowired
    public BusinessServiceImpl(Transformer transformer,
                               Generator generator,
                               Connector connector,
                               Parser parser,
                               BackTransformer backTransformer,
                               TransactionService transactionService,
                               AuthTransactionService authTransactionService) {
        this.transformer = transformer;
        this.generator = generator;
        this.connector = connector;
        this.parser = parser;
        this.backTransformer = backTransformer;
        this.transactionService = transactionService;
        this.authTransactionService = authTransactionService;
    }

    @Override
    public Transaction performTransaction(Transaction transaction) {

        AuthTransaction authTransaction = transformer.transform(transaction);
        String request = generator.generate(authTransaction);
        String response = connector.sendData(request);
        parser.parse(authTransaction, response);
        backTransformer.transform(transaction, authTransaction);

        transaction = transactionService.saveEntity(transaction);
        if (transaction != null) {
            authTransaction.setTransaction(transaction);
            authTransactionService.saveEntity(authTransaction);
        }

        return transaction;
    }
}
