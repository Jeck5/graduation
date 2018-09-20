package com.example.food.model;

/*TODO felete everything what is not necessary for tsk*/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bankaccounts") /*TODO fix underline*/
public class BankAccount extends AbstractBaseEntity {
    @Getter
    @Setter
    @Column(name = "number") /*TODO repeat it*/
    private Integer number; /*TODO perhaps other name*/

    @Getter
    @Setter
    @Column(name = "balance")
    private BigDecimal balance;
}
