package com.example.food.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity {
    //private BigDecimal price;//TODO how map

    @Column(name = "price")
    @NotNull
    private Integer price;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @JsonIgnore
    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;


}
