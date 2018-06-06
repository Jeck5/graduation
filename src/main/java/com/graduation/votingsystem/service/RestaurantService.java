package com.graduation.votingsystem.service;

import com.graduation.votingsystem.model.Restaurant;
import com.graduation.votingsystem.util.exception.NotFoundException;

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
