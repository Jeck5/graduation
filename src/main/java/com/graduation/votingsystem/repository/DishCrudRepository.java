package com.graduation.votingsystem.repository;

import com.graduation.votingsystem.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
@Repository
public interface DishCrudRepository extends JpaRepository<Dish,Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int  restaurantId);

    @Override
    @Transactional
    Dish save(Dish dish);

    //EntityGraph()
    @Query("SELECT d FROM Dish d JOIN FETCH d.restaurant WHERE d.restaurant.id=:restaurantId ORDER BY d.name")
    List<Dish> getAll(@Param("restaurantId") int  restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId AND d.date=:date ORDER BY d.name")
    List<Dish> getForFixedDate(@Param("restaurantId") int  restaurantId, @Param("date")LocalDate date);
}
