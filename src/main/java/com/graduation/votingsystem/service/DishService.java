package com.graduation.votingsystem.service;

import com.graduation.votingsystem.model.Dish;
import com.graduation.votingsystem.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface DishService {
    Dish get(int id, int restaurantId) throws NotFoundException;

    void delete(int id, int restaurantId) throws NotFoundException;

    List<Dish> getAll(int restaurantId);

    List<Dish> getForFixedDate(int  restaurantId, LocalDate date);

    Dish update(Dish dish, int restaurantId) throws NotFoundException;

    Dish create(Dish dish, int restaurantId);
}
