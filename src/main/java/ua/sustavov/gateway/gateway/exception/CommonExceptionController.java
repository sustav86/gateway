package ua.sustavov.gateway.gateway.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonExceptionController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleIOException(Exception ex) {

        ModelAndView model = new ModelAndView("error/error.jsp");
        model.addObject("errMsg", ex.getMessage());

        return model;

    }
}
