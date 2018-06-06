package com.example.food;

import com.example.food.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthorizedUser {

    @Autowired
    private UserService service;

    public int id() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return service.getByEmail(auth.getName()).getId();
    }
}