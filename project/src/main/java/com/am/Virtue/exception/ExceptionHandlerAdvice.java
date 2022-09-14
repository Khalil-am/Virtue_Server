package com.am.Virtue.exception;

import com.am.Virtue.config.MessageBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<MessageBody> handleException(ResourceException exception) {
        MessageBody messageBody = MessageBody.getInstance();
        messageBody.setStatus(exception.getHttpStatus().toString());
        messageBody.setText(exception.getMessage());
        return new ResponseEntity<>(messageBody, exception.getHttpStatus());
    }
}

