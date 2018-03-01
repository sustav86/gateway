package ua.sustavov.gateway.gateway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.sustavov.gateway.gateway.entity.Transaction;
import ua.sustavov.gateway.gateway.service.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class TransactionController {

    private final BusinessService businessService;
    private final ObjectMapper objectMapper;
    private final DozerBeanMapper dozerBeanMapper;

    @Autowired
    public TransactionController(BusinessService businessService,
                                 ObjectMapper objectMapper,
                                 DozerBeanMapper dozerBeanMapper) {
        this.businessService = businessService;
        this.objectMapper = objectMapper;
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @ResponseStatus(OK)
    @RequestMapping(value = "/api", method = {RequestMethod.GET})
    public ResponseEntity<?> createTransactionString(@RequestParam Map<String, String> queryMap) {

        Transaction transaction = dozerBeanMapper.map(queryMap, Transaction.class);

        transaction = businessService.performTransaction(transaction);

        if (transaction == null) {
            return new ResponseEntity<>("Failed to create new Transaction using given data.", BAD_REQUEST);
        }

        return new ResponseEntity<>(transaction, OK);

    }

    @ResponseStatus(OK)
    @RequestMapping(value = "/api", consumes = "application/json", method = {RequestMethod.POST})
    public ResponseEntity<?> createTransaction(@RequestBody String json) throws IOException {

        Map<String, Object> jsonMap = new HashMap<>();
        json = null;
        try {
            jsonMap = objectMapper.readValue(json, Map.class);
        } catch (IOException e) {
            throw new IOException("Bad request");
        }

        Transaction transaction = dozerBeanMapper.map(jsonMap, Transaction.class);

        transaction = businessService.performTransaction(transaction);

        if (transaction == null) {
            return new ResponseEntity<>("Failed to create new Transaction using given data.", BAD_REQUEST);
        }

        return new ResponseEntity<>(transaction, OK);

    }

    @RequestMapping("/")
    public String HelloController() {
        return "Hi! Simple gateway payment" + "<br>" +
                "Path for request: https://host/api" + "<br>" +
                "Good luck!";
    }

}
