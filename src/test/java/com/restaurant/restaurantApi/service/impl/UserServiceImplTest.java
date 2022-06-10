package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.Category;
import com.restaurant.restaurantApi.repo.IUserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceImplTest {

    @Mock
    private IUserRepo userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    private Category category = new Category();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        category.setCategory(1);
        category.setIdCategory(1);
        category.setNameCategory("Comidas");

    }
}
