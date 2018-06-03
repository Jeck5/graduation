package com.example.food.service;

import com.example.food.FoodApplication;
import com.example.food.model.Vote;
import com.example.food.repository.VoteCrudRepository;
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
//@SpringApplicationConfiguration(classes = FoodApplication.class)
@TestPropertySource(locations="classpath:application.properties")
public class VoteServiceTest {

    @Autowired
    VoteService service;

    @Test
    public void get() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getBetweenForRestaurant() {
        List<Vote> list = service.getBetweenForRestaurant(1004,
                LocalDate.of(2016,1,1),LocalDate.of(2019,1,1));
        list.size();
    }

    @Test
    public void getBetweenForUser() {
    }

    @Test
    public void update() {
    }

    @Test
    public void create() {
    }
}