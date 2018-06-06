package com.graduation.votingsystem.service;

import com.graduation.votingsystem.model.Dish;
import com.graduation.votingsystem.repository.DishCrudRepository;
import com.graduation.votingsystem.repository.RestaurantCrudRepository;
import com.graduation.votingsystem.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static com.graduation.votingsystem.util.ValidationUtil.checkNotFound;
import static com.graduation.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishServiceImpl implements DishService {

    private final DishCrudRepository repository;

    private final RestaurantCrudRepository restaurantRepository;

    @Autowired
    public DishServiceImpl(DishCrudRepository repository, RestaurantCrudRepository restaurantRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Dish get(int id, int restaurantId) throws NotFoundException {
        Dish dish = repository.findOne(id);
        if (dish.getRestaurant().getId() != restaurantId) {
            dish = null;
        }
        return checkNotFoundWithId(dish, id);
    }

    @Override
    public void delete(int id, int restaurantId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    @Override
    public List<Dish> getForFixedDate(int restaurantId, LocalDate date) {
        return repository.getForFixedDate(restaurantId,date);
    }

    @Override
    public Dish update(Dish dish, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(save(dish, restaurantId), dish.getId());
    }

    @Override
    public Dish create(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        return save(dish, restaurantId);
    }

    @Transactional
    protected Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.getId(), restaurantId) == null) {
            return null;
        }
        dish.setRestaurant(restaurantRepository.getOne(restaurantId));
        return repository.save(dish);
    }

}
