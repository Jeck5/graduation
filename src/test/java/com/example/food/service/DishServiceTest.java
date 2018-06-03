package com.example.food.service;

import com.example.food.FoodApplication;
import com.example.food.model.Dish;
import com.example.food.model.Restaurant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FoodApplication.class)
@TestPropertySource(locations="classpath:application.properties")
public class DishServiceTest {

    @Autowired
    private DishService service;
    @Test
    public void get() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getAll() {
        List<Dish> list = service.getAll(1005);
        Restaurant restaurant = list.get(1).getRestaurant();
        list.size();
    }

    @Test
    public void getForFixedDate() {
    }

    @Test
    public void update() {
    }

    @Test
    public void create() {
    }
}