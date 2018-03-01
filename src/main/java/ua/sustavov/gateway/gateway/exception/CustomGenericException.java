package ua.sustavov.gateway.gateway.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomGenericException extends RuntimeException {

    private String errCode;
    private String errMsg;

}
