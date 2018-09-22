package com.example.bankaccount.web;

import com.example.bankaccount.model.BankAccount;
import com.example.bankaccount.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

import static com.example.bankaccount.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(BankAccountRestController.REST_URL)
public class BankAccountRestController {
    static final String REST_URL = "/bankaccount";

    @Autowired
    private BankAccountService service;


    /* TODO check */
    @PostMapping(value = "/{id}")
    public ResponseEntity<BankAccount> createWithLocation(@PathVariable("id") int id) {
        BankAccount bankAccount = new BankAccount(id, BigDecimal.ZERO);
        checkNew(bankAccount);
        BankAccount created = service.create(bankAccount);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created); //TODO in aa proper way
    }

    @PutMapping(value = "/{id}/deposit")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deposit(@PathVariable("id") int id, @RequestParam("sum") BigDecimal sum){
        int i = 0;
        service.deposit(id,sum);
    }

    @PutMapping(value = "/{id}/withdraw")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void withdraw(@PathVariable("id") int id, @RequestParam("sum") BigDecimal sum){
        service.withdraw(id,sum);
    }

    @GetMapping(value = "/{id}/balance")
    public BigDecimal balance(@PathVariable("id") int id){
        return service.getById(id).getBalance();
    }



}
