package com.example.food.service;

import com.example.food.FoodApplication;
import com.example.food.model.Restaurant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FoodApplication.class)
@TestPropertySource(locations="classpath:application.properties")
public class RestaurantServiceTest {

    @Autowired
    protected RestaurantService service;

    @Test
    public void getAll() {
        List<Restaurant> list = service.getAll();
        list.size();
    }

    @Test
    public void getAllwithMenuOnDate() {
        List<Restaurant> list= service.getAllwithMenuOnDate(LocalDate.of(2018, 5, 9));
        list.size();
    }
}