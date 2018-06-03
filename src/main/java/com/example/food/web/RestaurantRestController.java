package com.example.food.web;

import com.example.food.model.Restaurant;
import com.example.food.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.example.food.util.ValidationUtil.assureIdConsistent;
import static com.example.food.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(RestaurantRestController.REST_URL)
public class RestaurantRestController {

    static final String REST_URL = "/rest/admin/restaurants";

    @Autowired
    private RestaurantService service;

    @GetMapping()
    public List<Restaurant> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Restaurant get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody @Valid Restaurant restaurant) {
        checkNew(restaurant);
        Restaurant created = service.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody @Valid Restaurant restaurant, @PathVariable("id") int id) {
        assureIdConsistent(restaurant, id);
        service.update(restaurant);
    }

}
