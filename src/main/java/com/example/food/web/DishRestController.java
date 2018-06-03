package com.example.food.web;

import com.example.food.model.Dish;
import com.example.food.model.Restaurant;
import com.example.food.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static com.example.food.util.ValidationUtil.assureIdConsistent;
import static com.example.food.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(DishRestController.REST_URL)
public class DishRestController {
    static final String REST_URL = RestaurantRestController.REST_URL + "/{restaurant_id}/dishes/";

    @Autowired
    private DishService service;

    @GetMapping("/{date}")
    public List<Dish> getForFixedDate(@PathVariable("restaurant_id") int restaurantId, @PathVariable("date") LocalDate date) {
        return service.getForFixedDate(restaurantId,date);
    }

    @GetMapping()
    public List<Dish> getAll(@PathVariable("restaurant_id") int restaurantId) {
        return service.getAll(restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@PathVariable("restaurant_id") int restaurantId, @RequestBody @Valid Dish dish) {
        checkNew(dish);
        Dish created = service.create(dish,restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("restaurant_id") int restaurantId, @PathVariable("id") int id) {
        service.delete(id,restaurantId);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody @Valid Dish dish, @PathVariable("restaurant_id") int restaurantId,@PathVariable("id") int id) {
        assureIdConsistent(dish, id);
        service.update(dish, restaurantId);
    }

}
