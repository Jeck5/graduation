package com.example.food.web;

import com.example.food.AuthorizedUser;
import com.example.food.model.Restaurant;
import com.example.food.model.Vote;
import com.example.food.service.DishService;
import com.example.food.service.RestaurantService;
import com.example.food.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

    @Autowired
    private AuthorizedUser authorizedUser;

    @GetMapping("/menu")
    List<Restaurant> getMenu(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "date") LocalDate date) {
        return restaurantService.getAllwithMenuOnDate(date);
    }

    @PostMapping(path = "/vote", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Vote createOrUpdate(@RequestBody int restaurantId) {
        return voteService.createOrUpdate(restaurantId, authorizedUser.id());

    }

}
