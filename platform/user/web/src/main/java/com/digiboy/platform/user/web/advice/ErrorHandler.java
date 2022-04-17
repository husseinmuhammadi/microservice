package com.digiboy.platform.user.web.advice;

import com.digiboy.platform.user.exception.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ResponseBody
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Access denied message here", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({UsernameAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleUsernameAlreadyExistsException(UsernameAlreadyExistsException e, WebRequest request){
        return  new ErrorMessage(100, new Date(), e.getMessage(), "");
    }
}
