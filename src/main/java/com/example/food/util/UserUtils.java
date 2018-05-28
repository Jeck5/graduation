package com.example.food.util;

import com.example.food.model.User;

public class UserUtils {
    public static User give(int id){
        User user = new User();
       // user.setPassword("pass"+id);
        return user;
    }
}
