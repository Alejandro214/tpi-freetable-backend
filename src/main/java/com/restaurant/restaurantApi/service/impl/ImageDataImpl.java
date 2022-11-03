package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.Util.ImageUtils;
import com.restaurant.restaurantApi.model.ImageData;
import com.restaurant.restaurantApi.repo.IImageDataRepo;
import com.restaurant.restaurantApi.service.inter.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataImpl implements IImageService {

    @Autowired
    private IImageDataRepo iImageDataRepo;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {

        ImageData imageData = iImageDataRepo.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }
    @Override
    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = iImageDataRepo.findByName(fileName);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}
