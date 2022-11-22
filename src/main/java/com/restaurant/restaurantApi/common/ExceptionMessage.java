package com.restaurant.restaurantApi.common;

import lombok.Getter;

@Getter
public enum ExceptionMessage {
    SAVE_ORDER_ERROR("¡Ocurrio un error al guardar el pedido!"),
    DELETE_ORDER_EXCEPTION("¡Error no se pudo eliminar la order!"),
    SAVE_PRODUCT_EXCEPTION("¡Ocurrio un errror al intentar guardar el producto!"),
    SAVE_MESA_EXCEPTION("Ocurrio un error al intentar guardar la mesa!"),
    ADD_ORDER_BY_ID_MESA_EXCEPTION("¡Ocurrio un error al intentar agregar el pedido al la mesa con dicho id!"),
    UPDATE_ORDER_EXCEPTION("¡Ocurrio un error al intentar updatear el pedido!");


    ExceptionMessage(String message){value = message;}

    private final String value;


}
