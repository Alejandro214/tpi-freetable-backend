package com.restaurant.restaurantApi.handler;

import com.restaurant.restaurantApi.exception.ControllerHandlerExceptionResponse;
import com.restaurant.restaurantApi.exception.SaveOrderBadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice()
@RestController
public class MenuHandlerException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception e, WebRequest request){
        ControllerHandlerExceptionResponse response = new ControllerHandlerExceptionResponse(e.getMessage(),request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(SaveOrderBadRequestException.class)
    public ResponseEntity<Object> handleSaveOrderBadRequestException(Exception e,WebRequest request){
        ControllerHandlerExceptionResponse response = new ControllerHandlerExceptionResponse(e.getMessage(),request.getDescription(false), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }

}
