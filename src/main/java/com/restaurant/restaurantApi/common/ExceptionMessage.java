package com.restaurant.restaurantApi.common;

import lombok.Getter;

@Getter
public enum ExceptionMessage {
    INCORRECT_ACCOUNT_INFO("Les informations fournies sont incorrectes or nulles"),
    INCORRECT_HASH_NUMBER_AND_CARD_NUMBER("Hash number or card card number not found");

    ExceptionMessage(String message){value = message;}

    private final String value;


}
