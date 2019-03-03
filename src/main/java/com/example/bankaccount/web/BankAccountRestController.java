package com.example.bankaccount.web;

import com.example.bankaccount.model.BankAccount;
import com.example.bankaccount.service.BankAccountService;
import com.example.bankaccount.util.AccountAction;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

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

    @PostMapping(value = "/{accountNumber}")
    public BankAccount create(@PathVariable("accountNumber")
                              @Range(min = MIN_VALUE, max = MAX_VALUE) int accountNumber) {
        BankAccount bankAccount = new BankAccount(accountNumber, BigDecimal.ZERO);
        checkNew(bankAccount);
        return service.create(bankAccount);
    }

    @PutMapping(value = "/{accountNumber}/deposit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deposit(@PathVariable("accountNumber")
                        @Range(min = MIN_VALUE, max = MAX_VALUE) int accountNumber, @RequestBody
                        @DecimalMin("0.00")
                        @Digits(integer = 100, fraction = 2) BigDecimal sum) {
        service.operate(accountNumber, sum, AccountAction.DEPOSIT);
    }

    @PutMapping(value = "/{accountNumber}/withdraw", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void withdraw(@PathVariable("accountNumber")
                         @Range(min = MIN_VALUE, max = MAX_VALUE) int accountNumber, @RequestBody
                         @DecimalMin("0.00")
                         @Digits(integer = 100, fraction = 2) BigDecimal sum) {
        service.operate(accountNumber, sum, AccountAction.WITHDRAW);
    }

    @GetMapping(value = "/{accountNumber}/balance")
    public BigDecimal balance(@PathVariable("accountNumber")
                              @Range(min = MIN_VALUE, max = MAX_VALUE) int accountNumber) {
        return service.getByAccountNumber(accountNumber).getBalance();
    }
}
