package com.graduation.votingsystem.repository;

import com.graduation.votingsystem.VotingSystemApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = VotingSystemApplication.class)
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