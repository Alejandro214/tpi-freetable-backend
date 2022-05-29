package com.restaurant.restaurantApi.common;

import lombok.Getter;

@Getter
public enum ExceptionMessage {
    SAVE_ORDER_ERROR("¡Ocurrio un error al guardar el pedido!"),
    GER_ALL_ORDERS_BY_MESA_EXCEPTION_("¡Ocurrio un error al intentar encontrar los pedido de la mesa!"),
    DELETE_ORDER_EXCEPTION("¡Error no se pudo eliminar la order!"),
    SAVE_PRODUCT_EXCEPTION("¡Ocurrio un errror al intentar guardar el producto!"),
    SEARCH_PRODUCT_EXCEPTION("¡Error al intentar el producto!"),
    GET_ALL_PRODUCTS_EXCEPTION("¡Error al intentar obtener todas las ordenes"),
    FILTER_PRODUCTS_BY_NAME("¡Ocurrio un error al intentar filtrar los productos por nombre!"),
    GET_PRODUCT_BY_NAME_CATEGORY("¡Ocurrio un error al intentar obtener los productos de la categoria dada!"),
    GET_CANT_PRODUCTOS_BY_NAME_CATEGORY("¡Ocurrio un error al intentar obtener la cantidad de productos de cierta categoria!"),
    SAVE_MESA_EXCEPTION("Ocurrio un error al intentar guardar la mesa!"),
    GET_MESA_BY_ID_EXCEPTION("¡Ocurrio un error al intentar buscar la mesa con dicho id!"),
    FIND_ALL_MESAS_EXCEPTION("¡Ocurrio un error al intentar buscar todas las mesas"),
    ADD_ORDER_BY_ID_MESA_EXCEPTION("¡Ocurrio un error al intentar agregar el pedido al la mesa con dicho id!");

    ExceptionMessage(String message){value = message;}

    private final String value;


}
