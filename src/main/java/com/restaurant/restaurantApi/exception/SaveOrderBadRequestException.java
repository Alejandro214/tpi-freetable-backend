package com.restaurant.restaurantApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SaveOrderBadRequestException extends RuntimeException {

    public SaveOrderBadRequestException(String message){
        super(message);
    }

}
