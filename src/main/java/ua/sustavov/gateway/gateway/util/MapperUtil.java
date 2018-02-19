package ua.sustavov.gateway.gateway.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.BeanUtils;
import ua.sustavov.gateway.gateway.dto.AuthTransactionDto;
import ua.sustavov.gateway.gateway.dto.TransactionDto;
import ua.sustavov.gateway.gateway.entity.AuthTransaction;
import ua.sustavov.gateway.gateway.entity.Transaction;

public class MapperUtil {

    public static Transaction toEntity(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionDto, transaction);

        return transaction;
    }

    public static AuthTransaction toAuthTransaction(Transaction transaction) {
        AuthTransaction authTransaction = new AuthTransaction();
        BeanUtils.copyProperties(transaction, authTransaction);

        return authTransaction;
    }

    public static AuthTransactionDto toAuthTransactionDto(TransactionDto transactionDto) {

        ObjectMapper objectMapper = new ObjectMapper();
        AuthTransactionDto authTransactionDto = objectMapper.convertValue(transactionDto, AuthTransactionDto.class);

        return authTransactionDto;
    }

}
