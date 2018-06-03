package com.example.food.repository;

import com.example.food.model.Restaurant;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface RestaurantCrudRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Restaurant save(Restaurant restaurant);

    //@Override
    Optional<Restaurant> findById(Integer id);

    @Override
    List<Restaurant> findAll(Sort sort);

    @Query("SELECT DISTINCT r from Restaurant r JOIN FETCH r.menuHistory m WHERE m.date=:curdate")
    List<Restaurant> findAllWithMenuOnDate(@Param("curdate") LocalDate curDate);

}
