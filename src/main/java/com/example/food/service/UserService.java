package com.example.food.service;

import com.example.food.model.User;
import com.example.food.util.exception.NotFoundException;

import java.util.List;

/**
 * Author: nicolas
 * Date: 16.11.2016.
 */
public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user);

    List<User> getAll();

    void enable(int id, boolean enable);
}
