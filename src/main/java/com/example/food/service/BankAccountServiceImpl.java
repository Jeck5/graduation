package com.example.food.service;

import com.example.food.model.BankAccount;
import com.example.food.model.User;
import com.example.food.repository.BankAccountCrudRepository;
import com.example.food.repository.UserCrudRepository;
import com.example.food.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;

import static com.example.food.util.ValidationUtil.checkNotFound;
import static com.example.food.util.ValidationUtil.checkNotFoundWithId;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountCrudRepository repository;

    @Autowired
    public BankAccountServiceImpl(BankAccountCrudRepository repository) {
        this.repository = repository;
    }

    @Override
    public BankAccount create(BankAccount bankAccount) {
        Assert.notNull(bankAccount, "bankAccount must not be null");
        return repository.save(bankAccount);
    }

    @Override
    public void update(BankAccount bankAccount, BigDecimal sum) {
        /*TODO add business logic*/
        Assert.notNull(bankAccount, "bankAccount must not be null");
        checkNotFoundWithId(repository.save(bankAccount), bankAccount.getId());

    }

    @Override
    public BankAccount getByNumber(Integer number) throws NotFoundException {
        Assert.notNull(number, "number must not be null");
        return checkNotFound(repository.findByNumber(number).orElse(null), "number=" + number);
    }
}
