package com.example.food.service;

import com.example.food.model.Restaurant;
import com.example.food.model.User;
import com.example.food.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    void delete(int id) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    void update(Restaurant restaurant);

    List<Restaurant> getAll();

    List<Restaurant> getAllwithMenuOnDate(LocalDate date);

}
