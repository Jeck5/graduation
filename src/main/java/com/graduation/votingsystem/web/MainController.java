package com.graduation.votingsystem.web;

import com.graduation.votingsystem.model.User;
import com.graduation.votingsystem.repository.UserCrudRepository;
import com.graduation.votingsystem.util.ApplicationContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food") //TODO delete it or make for registration
public class MainController {

    @Autowired
    UserCrudRepository userRepository;

    @Autowired
    ApplicationContextProvider applicationContextProvider;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id) {
        int tmp = 1;
        //return UserUtils.give(id);
        applicationContextProvider.getContext();
        return userRepository.findOne(id);
    }
}
