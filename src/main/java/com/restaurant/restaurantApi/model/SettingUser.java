package com.restaurant.restaurantApi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SettingUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSettingUser;
    private Integer idUser;
    @ManyToOne
    @JoinColumn(name = "image_data_id_image")
    private ImageData imageData;


}
