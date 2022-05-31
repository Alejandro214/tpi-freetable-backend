package com.restaurant.restaurantApi.model;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@DiscriminatorValue("combo")
@Table(name = "combo")
public class Combo extends Product {

    private String from;
    private String to;


    public void dateFrom(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        this.from = dateFormat.format(date);
    }
}
