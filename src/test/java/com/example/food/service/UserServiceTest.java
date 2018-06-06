package com.example.food.service;

import com.example.food.FoodApplication;
import com.example.food.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.boot.test.S;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FoodApplication.class)
//@SpringApplicationConfiguration(classes = FoodApplication.class)
@TestPropertySource(locations="classpath:application.properties")
public class UserServiceTest {

    @Autowired
    protected UserService service;

    @Test
    public void create() {
        //service.create(new User(11));
        new User().getClass().getConstructors();
    }

    @Test
    public void delete() {
        service.delete(1002);
        service.delete(1005);
    }

    @Test
    public void get() {
    }

    @Test
    public void getByEmail() {
        User u = service.getByEmail("john@mail.com");
        u.getId();
    }

    @Test
    public void update() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void enable() {
    }
}