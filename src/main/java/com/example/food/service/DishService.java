package com.example.food.service;

import com.example.food.model.Dish;
import com.example.food.util.exception.NotFoundException;

import java.util.List;

public interface DishService {
    Dish get(int id, int restaurantId) throws NotFoundException;

    void delete(int id, int restaurantId) throws NotFoundException;

    List<Dish> getAll(int restaurantId);

    Dish update(Dish dish, int restaurantId) throws NotFoundException;

    Dish create(Dish dish, int restaurantId);
}
