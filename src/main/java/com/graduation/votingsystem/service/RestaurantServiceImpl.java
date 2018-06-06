package com.graduation.votingsystem.service;

import com.graduation.votingsystem.model.Restaurant;
import com.graduation.votingsystem.repository.DishCrudRepository;
import com.graduation.votingsystem.repository.RestaurantCrudRepository;
import com.graduation.votingsystem.util.exception.NotFoundException;
import com.graduation.votingsystem.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.graduation.votingsystem.util.ValidationUtil.checkNotFoundWithId;

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
        ValidationUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return ValidationUtil.checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        ValidationUtil.checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
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
