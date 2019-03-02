package com.example.bankaccount.web;

import com.example.bankaccount.model.BankAccount;
import com.example.bankaccount.service.BankAccountService;
import com.example.bankaccount.util.AccountAction;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.net.URI;

import static com.example.bankaccount.util.ValidationUtil.checkNew;

@RestController
@Validated
@RequestMapping(BankAccountRestController.REST_URL)
public class BankAccountRestController {
    static final String REST_URL = "/bankaccount";

    private static final int MIN_VALUE = 10000;
    private static final int MAX_VALUE = 99999;

    @Autowired
    private BankAccountService service;

    @PostMapping(value = "/{id}")
    public BankAccount create(@PathVariable("id")
                              @Range(min = MIN_VALUE, max = MAX_VALUE) int id) {
        BankAccount bankAccount = new BankAccount(id, BigDecimal.ZERO);
        checkNew(bankAccount);
        return service.create(bankAccount);
    }

    @PutMapping(value = "/{id}/deposit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deposit(@PathVariable("id")
                        @Range(min = MIN_VALUE, max = MAX_VALUE) int id, @RequestBody
                        @DecimalMin("0.00")
                        @Digits(integer = 100, fraction = 2) BigDecimal sum) {
        service.operate(id, sum, AccountAction.DEPOSIT);
    }

    @PutMapping(value = "/{id}/withdraw", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void withdraw(@PathVariable("id")
                         @Range(min = MIN_VALUE, max = MAX_VALUE) int id, @RequestBody
                         @DecimalMin("0.00")
                         @Digits(integer = 100, fraction = 2) BigDecimal sum) {
        service.operate(id, sum, AccountAction.WITHDRAW);
    }

    @GetMapping(value = "/{id}/balance")
    public BigDecimal balance(@PathVariable("id")
                              @Range(min = MIN_VALUE, max = MAX_VALUE) int id) {
        return service.getById(id).getBalance();
    }
}
