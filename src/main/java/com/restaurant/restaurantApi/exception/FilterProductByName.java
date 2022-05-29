package com.restaurant.restaurantApi.exception;

public class FilterProductByName extends RuntimeException {

    public FilterProductByName(String message){
        super(message);
    }

}