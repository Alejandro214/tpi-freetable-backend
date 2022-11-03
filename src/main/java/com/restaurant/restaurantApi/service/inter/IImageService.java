package com.restaurant.restaurantApi.service.inter;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImageService {
     String uploadImage(MultipartFile file) throws IOException;
     byte[] downloadImage(String fileName);

}
