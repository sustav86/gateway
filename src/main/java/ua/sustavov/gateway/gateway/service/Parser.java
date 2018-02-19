package ua.sustavov.gateway.gateway.service;

import ua.sustavov.gateway.gateway.entity.AuthTransaction;

public interface Parser {

    void parse(AuthTransaction authTransaction, String responseContent);

}
