package com.restaurant.restaurantApi.controller;

import com.restaurant.restaurantApi.dto.Mensaje;
import com.restaurant.restaurantApi.model.SettingUser;
import com.restaurant.restaurantApi.service.inter.ISettingUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("setting")
@Tag(name = "Setting User Controller", description = "API para gestión de las credenciales del usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SettingUserController {

    @Autowired
    private ISettingUserService iSettingUserService;

    @GetMapping("/settings/{username}")
    public ResponseEntity<SettingUser> getSettingByUsername(@PathVariable String username) {
        SettingUser settingUser = iSettingUserService.findByUsername(username);
        return ResponseEntity.ok(settingUser);
    }

    @PostMapping("/settings")
    public ResponseEntity<?> saveSettingUser(@Valid @RequestBody SettingUser settingUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new Mensaje("Error, campos inválidos"));
        }
        SettingUser savedSetting = iSettingUserService.saveSettingUser(settingUser);
        return ResponseEntity.ok(savedSetting);
    }

}
