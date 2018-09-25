package com.example.bankaccount.model;


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
@Table(name = "bankaccounts")
public class BankAccount extends AbstractBaseEntity {
    @Getter
    @Setter
    @Column(name = "id")
    private Integer id;

    @Getter
    @Setter
    @Column(name = "balance")
    private BigDecimal balance;
}
