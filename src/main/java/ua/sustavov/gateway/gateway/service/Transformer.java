package ua.sustavov.gateway.gateway.service;

import ua.sustavov.gateway.gateway.dto.AuthTransactionDto;
import ua.sustavov.gateway.gateway.entity.AuthTransaction;

public interface Transformer {

    AuthTransaction transform(AuthTransactionDto authTransactionDto);

}
