package ua.sustavov.gateway.gateway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.sustavov.gateway.gateway.dto.TransactionDto;
import ua.sustavov.gateway.gateway.mapper.QueryStringMapper;
import ua.sustavov.gateway.gateway.service.*;

import javax.ws.rs.BeanParam;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class TransactionController {

    private final BusinessService businessService;
    private final ObjectMapper objectMapper;

    @Autowired
    public TransactionController(BusinessService businessService,
                                 ObjectMapper objectMapper) {
        this.businessService = businessService;
        this.objectMapper = objectMapper;
    }

    @ResponseStatus(OK)
    @RequestMapping(value = "/api", method = {RequestMethod.GET})
    public ResponseEntity<?> createTransactionString(@BeanParam QueryStringMapper queryStringMapper) {

        TransactionDto transactionDto = objectMapper.convertValue(queryStringMapper, TransactionDto.class);

        transactionDto = businessService.performTransaction(transactionDto);
        if (transactionDto == null) {
            return new ResponseEntity<>("Failed to create new Transaction using given data.", BAD_REQUEST);
        }
        return new ResponseEntity<>(transactionDto, OK);

    }

    @ResponseStatus(OK)
    @RequestMapping(value = "/api", consumes = "application/json", method = {RequestMethod.POST})
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDto transactionDto) {

        transactionDto = businessService.performTransaction(transactionDto);
        if (transactionDto == null) {
            return new ResponseEntity<>("Failed to create new Transaction using given data.", BAD_REQUEST);
        }
        return new ResponseEntity<>(transactionDto, OK);

    }

    @RequestMapping("/")
    public String HelloController() {
        return "Hi! Simple gateway payment" + "<br>" +
                "Path for request: https://host/api" + "<br>" +
                "Good luck!";
    }

}
