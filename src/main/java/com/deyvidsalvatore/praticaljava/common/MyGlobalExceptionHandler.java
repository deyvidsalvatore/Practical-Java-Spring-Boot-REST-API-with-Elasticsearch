package com.deyvidsalvatore.praticaljava.common;

import com.deyvidsalvatore.praticaljava.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(MyGlobalExceptionHandler.class);

    @ExceptionHandler(value = IllegalArgumentException.class)
    private ResponseEntity<ErrorResponse> handleIllegalApiParamException(IllegalArgumentException e) {
        var message = "Exception API Param from MyGlobalExceptionHandler, " + e.getMessage();
        LOG.warn(message);

        var errorResponse = new ErrorResponse(message, LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
