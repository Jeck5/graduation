package com.example.bankaccount.service;

import com.example.bankaccount.model.BankAccount;
import com.example.bankaccount.util.AccountAction;
import com.example.bankaccount.util.exception.NotFoundException;

import java.math.BigDecimal;

public interface BankAccountService {
    BankAccount create(BankAccount bankAccount);

    void operate(int id, BigDecimal sum, AccountAction action);

    BankAccount getById(Integer id) throws NotFoundException;

}
