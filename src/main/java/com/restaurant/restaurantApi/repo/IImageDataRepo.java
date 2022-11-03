package com.restaurant.restaurantApi.repo;

import com.restaurant.restaurantApi.model.ImageData;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IImageDataRepo  extends CrudRepository<ImageData,Long> {


    Optional<ImageData> findByName(String fileName);
}
