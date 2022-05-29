package com.restaurant.restaurantApi.exception;

public class GetProductByNameCategory extends RuntimeException {

    public GetProductByNameCategory(String message){
        super(message);
    }

}