package com.restaurant.restaurantApi.controller;


import com.restaurant.restaurantApi.service.inter.IImageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("imagedata")
@Api
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImageDataController {

    @Autowired
    private IImageService imageService;

    @PostMapping("uploadImage")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = imageService.uploadImage(file);
        return new ResponseEntity<>(uploadImage,HttpStatus.OK);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] imageData=imageService.downloadImage(fileName);
       // return new ResponseEntity<>()
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);

    }

}
