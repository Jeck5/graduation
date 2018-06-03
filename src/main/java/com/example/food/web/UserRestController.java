package com.example.food.web;

import com.example.food.model.User;
import com.example.food.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(UserRestController.REST_URL)
public class UserRestController {
    static final String REST_URL = "/rest/admin/users";

    @Autowired
    private UserService service;

    @GetMapping()
    public List<User> getAll(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return service.getAll();
    }
    //TODO realise full or delete
}
