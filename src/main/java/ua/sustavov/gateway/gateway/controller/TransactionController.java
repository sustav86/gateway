package ua.sustavov.gateway.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.sustavov.gateway.gateway.dto.TransactionDto;
import ua.sustavov.gateway.gateway.service.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class TransactionController {

    private final BusinessService businessService;

    @Autowired
    public TransactionController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @ResponseStatus(OK)
    @RequestMapping(value = "/api", consumes = {"application/x-www-form-urlencoded", "text/xml", "text/plain"}, method = {RequestMethod.POST})
    public ResponseEntity<?> createTransactionString(@RequestBody TransactionDto transactionDto) {

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
