package com.graduation.votingsystem.web;

import com.graduation.votingsystem.AuthorizedUser;
import com.graduation.votingsystem.model.Restaurant;
import com.graduation.votingsystem.model.Vote;
import com.graduation.votingsystem.service.DishService;
import com.graduation.votingsystem.service.RestaurantService;
import com.graduation.votingsystem.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
