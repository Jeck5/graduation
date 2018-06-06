package com.graduation.votingsystem.service;

import com.graduation.votingsystem.VotingSystemApplication;
import com.graduation.votingsystem.model.Dish;
import com.graduation.votingsystem.model.Restaurant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = VotingSystemApplication.class)
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