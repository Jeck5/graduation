package com.graduation.votingsystem.service;

import com.graduation.votingsystem.VotingSystemApplication;
import com.graduation.votingsystem.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.boot.test.S;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = VotingSystemApplication.class)
//@SpringApplicationConfiguration(classes = VotingSystemApplication.class)
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