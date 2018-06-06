package com.graduation.votingsystem.service;

import com.graduation.votingsystem.model.Vote;
import com.graduation.votingsystem.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface VoteService {
    Vote get(int id, int restaurantId, int userId) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<Vote> getBetweenForRestaurant(int restaurantId, LocalDate startDate, LocalDate endDate);

    List<Vote> getBetweenForUser(int userId, LocalDate startDate, LocalDate endDate);

    Vote createOrUpdate(int restaurantId, int userId) throws NotFoundException;
}
