package com.graduation.votingsystem.service;

import com.graduation.votingsystem.VotingSystemApplication;
import com.graduation.votingsystem.model.Vote;
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
//@SpringApplicationConfiguration(classes = VotingSystemApplication.class)
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