package com.restaurant.restaurantApi.repo;

import com.restaurant.restaurantApi.model.Combo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IComboRepo  extends CrudRepository<Combo, Integer> {
    @Query(value = "SELECT c.idProduct,c.name,c.image,c.price,c.description,c.from,c.to, c.cantProduct FROM restaurant_db.combo c ", nativeQuery = true)
    List<Combo> findAllCombos();


    @Query(value = " select c.idProduct,c.name,c.image,c.price,c.description,c.from,c.to from table_order o " +
            "INNER JOIN productos_pedidos pp on pp.table_order = o.idOrder " +
            "INNer Join combo c on c.idProduct  = pp.product " +
            "where idMesa = :idMesa ",nativeQuery = true)
    List<Combo> findAllCombosByIdMesa(Integer idMesa);

}
