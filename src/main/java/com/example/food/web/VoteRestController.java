package com.example.food.web;


import com.example.food.model.Restaurant;
import com.example.food.model.Vote;
import com.example.food.service.RestaurantService;
import com.example.food.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(VoteRestController.REST_URL)
public class VoteRestController {
    static final String REST_URL = "/rest/admin/votes";

    @Autowired
    private VoteService service;

    @GetMapping("/user")
    public List<Vote> getBetweenForUser(@RequestParam(value = "user_id", required = true) int userId,
                                        @RequestParam(value = "startDate", required = false) LocalDate startDate,
                                        @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return service.getBetweenForUser(userId, startDate, endDate);

    }

    @GetMapping("/restaurant")
    public List<Vote> getBetweenForRestaurant(@RequestParam(value = "restaurant_id", required = true) int restaurantId,
                                              @RequestParam(value = "startDate", required = false) LocalDate startDate,
                                              @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return service.getBetweenForRestaurant(restaurantId, startDate, endDate);

    }
}
