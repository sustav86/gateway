package ua.sustavov.gateway.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.sustavov.gateway.gateway.dto.AuthTransactionDto;
import ua.sustavov.gateway.gateway.dto.TransactionDto;
import ua.sustavov.gateway.gateway.entity.AuthTransaction;
import ua.sustavov.gateway.gateway.entity.Transaction;
import ua.sustavov.gateway.gateway.service.*;
import ua.sustavov.gateway.gateway.util.JsonMapper;
import ua.sustavov.gateway.gateway.util.MapperUtil;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class TransactionController {

    private final Transformer transformer;
    private final Generator generator;
    private final Connector connector;
    private final BackTransformer backTransformer;
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(Transformer transformer,
                                 Generator generator,
                                 Connector connector,
                                 BackTransformer backTransformer,
                                 TransactionService transactionService) {
        this.transformer = transformer;
        this.generator = generator;
        this.connector = connector;
        this.backTransformer = backTransformer;
        this.transactionService = transactionService;
    }

    @PostMapping
    @ResponseStatus(OK)
    @RequestMapping("/api")
    public ResponseEntity<?> createTransaction(@RequestBody String json) {

        TransactionDto transactionDto = JsonMapper.MapToDto(json);
        Transaction transaction = MapperUtil.toEntity(transactionDto);
        AuthTransactionDto authTransactionDto = MapperUtil.toAuthTransactionDto(transactionDto);

        AuthTransaction authTransaction = transformer.transform(authTransactionDto);
        String request = generator.generate(authTransactionDto, json);
        String response = connector.sendData(request);
        AuthTransactionDto responseAuthTransactionDto = JsonMapper.ResponseToDto(authTransactionDto, response);
        authTransaction = transformer.transform(responseAuthTransactionDto);
        backTransformer.transform(transaction, authTransaction);

        Transaction saveTransaction = transactionService.saveEntity(transaction);
        if (saveTransaction == null) {
            return new ResponseEntity<>("Failed to create new Transaction", BAD_REQUEST);
        }

        return new ResponseEntity<>(saveTransaction, OK);
    }


    @RequestMapping("/")
    public String HelloController() {
        return "Hi! Simple gateway payment" + "<br>" +
                "Path for request: https://host/api" + "<br>" +
                "Good luck!";
    }

}
