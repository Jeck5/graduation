package com.example.food.repository;

import com.example.food.FoodApplication;
import com.example.food.model.Dish;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FoodApplication.class)
@TestPropertySource(locations="classpath:application.properties")

public class DishCrudRepositoryTest {


    @Autowired
    DishCrudRepository repository;


    @Test
    public void test()
    {
        //Dish  dish = repository.findOne(1010);
        //dish.getDate();
        //dish.getRestaurant();
    }

}