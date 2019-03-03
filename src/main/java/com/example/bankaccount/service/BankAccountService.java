package com.example.bankaccount.service;

import com.example.bankaccount.model.BankAccount;
import com.example.bankaccount.util.AccountAction;
import com.example.bankaccount.util.exception.NotFoundException;

import java.math.BigDecimal;

public interface BankAccountService {
    BankAccount create(BankAccount bankAccount);

    void operate(int accountNumber, BigDecimal sum, AccountAction action);

    BankAccount getByAccountNumber(Integer accountNumber) throws NotFoundException;

}
