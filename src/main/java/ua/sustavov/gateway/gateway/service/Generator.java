package ua.sustavov.gateway.gateway.service;

import ua.sustavov.gateway.gateway.entity.AuthTransaction;

public interface Generator {

    String generate(AuthTransaction authTransaction);

}
