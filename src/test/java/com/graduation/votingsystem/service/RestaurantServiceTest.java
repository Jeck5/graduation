package com.graduation.votingsystem.service;

import com.graduation.votingsystem.VotingSystemApplication;
import com.graduation.votingsystem.model.Restaurant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = VotingSystemApplication.class)
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