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

    @Autowired
    public BusinessServiceImpl(TransactionMapper transactionMapper,
                               Transformer transformer,
                               Generator generator,
                               Connector connector,
                               Parser parser,
                               BackTransformer backTransformer) {
        this.transactionMapper = transactionMapper;
        this.transformer = transformer;
        this.generator = generator;
        this.connector = connector;
        this.parser = parser;
        this.backTransformer = backTransformer;
    }

    @Override
    public TransactionDto performTransaction(TransactionDto transactionDto) {

        Transaction transaction = transactionMapper.toEntity(transactionDto);

        System.out.println(transaction);

//        Transformer
        AuthTransaction authTransaction = transformer.transform(transaction);

        System.out.println(authTransaction);

//        Generator
        String request = generator.generate(authTransaction);

        System.out.println(request);

//        Connector
        String response = connector.sendData(request);

        System.out.println(response);

//        Parser
        parser.parse(authTransaction, response);

//        BackTransformer
        backTransformer.transform(transaction, authTransaction);

        return null;
    }
}
