package com.restaurant.restaurantApi.controller;

import com.restaurant.restaurantApi.common.ExceptionMessage;
import com.restaurant.restaurantApi.exception.SaveMesaException;
import com.restaurant.restaurantApi.model.Mesa;
import com.restaurant.restaurantApi.model.SettingUser;
import com.restaurant.restaurantApi.service.inter.ISettingUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("setting")
@Api
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SettingUserController {

    @Autowired
    private ISettingUserService iSettingUserService;

    @GetMapping("getSettingByUsername/{username}")
    public ResponseEntity<SettingUser> getSettingByUsername(@PathVariable String username){
        return new ResponseEntity<>(this.iSettingUserService.findByUsername(username), HttpStatus.OK);
    }

    @PostMapping("saveSettingUser")
    public ResponseEntity<SettingUser> saveSettingUser(@RequestBody SettingUser settingUser){
        return new ResponseEntity<>(this.iSettingUserService.saveSettingUser(settingUser), HttpStatus.OK);

    }
}