package com.graduation.votingsystem.repository;

import com.graduation.votingsystem.VotingSystemApplication;
import com.graduation.votingsystem.model.Vote;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = VotingSystemApplication.class)
@TestPropertySource(locations="classpath:application.properties")
public class VoteCrudRepositoryTest {

    @Autowired
    VoteCrudRepository repository;

    @Test
    public void test() {
        Vote vote = repository.findOne(1021);
        vote.getTime();
    }
}