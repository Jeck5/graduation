package com.example.bankaccount.service;

import com.example.bankaccount.model.BankAccount;
import com.example.bankaccount.util.exception.NotFoundException;

import java.math.BigDecimal;

public interface BankAccountService {
    BankAccount create(BankAccount bankAccount);

    void deposit(int id, BigDecimal sum);

    void withdraw(int id, BigDecimal sum);

    BankAccount getById(Integer id) throws NotFoundException;

}
