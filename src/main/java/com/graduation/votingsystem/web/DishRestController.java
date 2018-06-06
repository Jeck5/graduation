package com.graduation.votingsystem.web;

import com.graduation.votingsystem.model.Dish;
import com.graduation.votingsystem.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.graduation.votingsystem.util.ValidationUtil.assureIdConsistent;
import static com.graduation.votingsystem.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(DishRestController.REST_URL)
public class DishRestController {
    static final String REST_URL = RestaurantRestController.REST_URL + "/{restaurant_id}/dishes";

    @Autowired
    private DishService service;

    @GetMapping("/{id}")
    public Dish get(@PathVariable("restaurant_id") int restaurantId, @PathVariable("id") int id) {
        return service.get(id, restaurantId);
    }

    @GetMapping()
    public List<Dish> getAll(@PathVariable("restaurant_id") int restaurantId) {
        return service.getAll(restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@PathVariable("restaurant_id") int restaurantId, @RequestBody Dish dish) {
        checkNew(dish);
        Dish created = service.create(dish,restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("restaurant_id") int restaurantId, @PathVariable("id") int id) {
        service.delete(id,restaurantId);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Dish dish, @PathVariable("restaurant_id") int restaurantId,@PathVariable("id") int id) {
        assureIdConsistent(dish, id);
        service.update(dish, restaurantId);
    }

}
