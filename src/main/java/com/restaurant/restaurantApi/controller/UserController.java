package com.restaurant.restaurantApi.controller;

import com.restaurant.restaurantApi.model.User;
import com.restaurant.restaurantApi.service.inter.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Api
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private IUserService iUserService;


    @PostMapping("saveUser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(this.iUserService.saveUser(user), HttpStatus.OK);
    }

    @GetMapping("findUserById/{idUser}")
    public ResponseEntity<User> findUserById(@PathVariable Integer idUser) {
        return new ResponseEntity<>(this.iUserService.findUserById(idUser), HttpStatus.OK);
    }

    @DeleteMapping("deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody User user){
        this.iUserService.deleteUser(user);
        return new ResponseEntity<>("Se ha eliminado con existo al usuario",HttpStatus.OK);
    }

    @PutMapping("updateRolUserById/{idUser}/{newRol}")
    public ResponseEntity<User> changeRolUserById(@PathVariable Integer idUser,@PathVariable String newRol){
        return new ResponseEntity<>(this.iUserService.changeRolUserById(idUser,newRol),HttpStatus.OK);
    }
}
