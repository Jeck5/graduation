package com.example.food.service;

import com.example.food.model.Dish;
import com.example.food.model.Vote;
import com.example.food.util.exception.NotFoundException;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface VoteService {
    Vote get(int id, int restaurantId, int userId) throws NotFoundException;

    void delete(int id) throws NotFoundException;//TODO othet parms??

    List<Vote> getBetweenForRestaurant(int restaurantId, LocalDate startDate, LocalDate endDate);

    List<Vote> getBetweenForUser(int userId, LocalDate startDate, LocalDate endDate);

    Vote update(Vote vote, int restaurantId, int userId) throws NotFoundException;

    Vote create(Vote vote, int restaurantId, int userId);
}
