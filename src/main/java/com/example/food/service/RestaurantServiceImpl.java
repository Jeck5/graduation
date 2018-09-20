package com.example.food.service;

import com.example.food.model.Restaurant;
import com.example.food.repository.DishCrudRepository;
import com.example.food.repository.RestaurantCrudRepository;
import com.example.food.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static com.example.food.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");

    private final RestaurantCrudRepository repository;
    private final DishCrudRepository dishRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantCrudRepository repository, DishCrudRepository dishRepository) {
        this.repository = repository;
        this.dishRepository = dishRepository;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.findAll(SORT_NAME);
    }

    @Override
    public List<Restaurant> getAllwithMenuOnDate(LocalDate date) {
        return repository.findAllWithMenuOnDate(date);
    }
}