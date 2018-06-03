package com.example.food.web;

import com.example.food.AuthorizedUser;
import com.example.food.model.Restaurant;
import com.example.food.model.Vote;
import com.example.food.service.DishService;
import com.example.food.service.RestaurantService;
import com.example.food.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.example.food.util.ValidationUtil.assureIdConsistent;
import static com.example.food.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController {
    static final String REST_URL = "/rest/profile";

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @Autowired
    private VoteService voteService;

    @GetMapping("/menu")
    List<Restaurant> getMenu(@RequestParam(value = "date") LocalDate date) {
        return restaurantService.getAllwithMenuOnDate(date);
    }

    @GetMapping("/today")
    List<Restaurant> getMenuToday() {//TODO today
        return restaurantService.getAllwithMenuOnDate(LocalDate.of(2018, 9, 5));
    }
//TODO try alien's vote , perhaps regardcode 11-00
    @PostMapping(path = "/vote", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@RequestBody @Valid Vote vote) {
        checkNew(vote);
        Vote created = voteService.create(vote, vote.getRestaurant().getId(), AuthorizedUser.id());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/vote" + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(path = "/vote", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody @Valid Vote vote, @PathVariable("id") int id) {
        assureIdConsistent(vote, id);
        voteService.update(vote, vote.getRestaurant().getId(), AuthorizedUser.id());
    }


}
