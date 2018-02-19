package ua.sustavov.gateway.gateway.service;

import ua.sustavov.gateway.gateway.dto.AuthTransactionDto;

public interface Generator {

    String generate(AuthTransactionDto authTransactionDto, String json);

}
