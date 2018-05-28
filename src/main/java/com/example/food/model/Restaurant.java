package com.example.food.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    private String info;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy(value = "date DESC")
    private List<Dish> menuHistory;
}
