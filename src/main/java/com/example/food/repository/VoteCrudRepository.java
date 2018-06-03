package com.example.food.repository;

import com.example.food.model.Dish;
import com.example.food.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
@Repository
public interface VoteCrudRepository extends JpaRepository<Vote,Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    @Modifying
    Vote save(Vote vote);

    @Query("SELECT v FROM Vote v LEFT JOIN FETCH v.restaurant WHERE v.restaurant.id=:restaurantId AND v.date BETWEEN :startDate AND :endDate ORDER BY v.date, v.time")
    List<Vote> getBetweenForRestaurant(@Param("restaurantId") int  restaurantId, @Param("startDate")LocalDate startDate,
                      @Param("endDate")LocalDate endDate);

    @Query("SELECT v FROM Vote v LEFT JOIN FETCH v.user WHERE v.user.id=:userId AND v.date BETWEEN :startDate AND :endDate ORDER BY v.date, v.time")
    List<Vote> getBetweenForUser(@Param("userId") int  userId, @Param("startDate")LocalDate startDate,
                                       @Param("endDate")LocalDate endDate);
}
