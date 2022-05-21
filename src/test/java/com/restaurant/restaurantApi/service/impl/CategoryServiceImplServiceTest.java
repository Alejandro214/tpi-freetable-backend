package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.Category;
import com.restaurant.restaurantApi.repo.ICategoryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CategoryServiceImplServiceTest {

    @Mock
    private ICategoryRepo categoryRepo;

    @InjectMocks
    private CategoryServiceImplService categoryServiceImplService;

    private Category category = new Category();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        category.setCategory(1);
        category.setIdCategory(1);
        category.setNameCategory("Comidas");

    }

    @Test
    void findByCategory() {
        when(this.categoryRepo.findByCategory(any(Integer.class))).thenReturn(category);
        Category category = this.categoryServiceImplService.findByCategory(1);
        assertEquals(1,category.getCategory());
        assertEquals("Comidas",category.getNameCategory());
        assertEquals(1,category.getIdCategory());
    }
}