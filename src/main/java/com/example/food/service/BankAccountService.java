package com.example.food.service;

import com.example.food.model.BankAccount;
import com.example.food.util.exception.NotFoundException;

import java.math.BigDecimal;

public interface BankAccountService {
    BankAccount create(BankAccount bankAccount);

    void update(BankAccount bankAccount, BigDecimal sum); /*TODO add action*/

    BankAccount getByNumber(Integer number) throws NotFoundException;

}
