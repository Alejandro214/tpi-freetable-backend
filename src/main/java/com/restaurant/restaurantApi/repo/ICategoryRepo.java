package com.restaurant.restaurantApi.repo;
import com.restaurant.restaurantApi.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ICategoryRepo extends CrudRepository<Category, Integer>{
    Category findByCategory(Integer category);
    List<Category> findAllByIdCategory(Integer idCategory);
    Category  findByNameCategory(String nameCategory);
    @Query(value ="    SELECT count(*) as cantidad_de_productos FROM restaurant_db.productos_category pc " +
            "   INNER JOIN category c ON pc.id_category = c.idCategory " +
            "   where c.nameCategory = :nameCategory ", nativeQuery = true)
    Integer cantProductosByNameCategory(String nameCategory);
}
