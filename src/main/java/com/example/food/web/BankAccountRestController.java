package com.example.food.web;

import com.example.food.model.BankAccount;
import com.example.food.model.Dish;
import com.example.food.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;

import static com.example.food.util.ValidationUtil.assureIdConsistent;
import static com.example.food.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(BankAccountRestController.REST_URL)
public class BankAccountRestController {
    static final String REST_URL = "/bankaccount";

    @Autowired
    private BankAccountService service;


    /* TODO this id is not a number, but we use as number, number must not be a part of rest */
    @PostMapping(value = "/{id}")
    public ResponseEntity<BankAccount> createWithLocation(@PathVariable("id") int number) {
        BankAccount bankAccount = new BankAccount(number, BigDecimal.ZERO);
        checkNew(bankAccount);
        BankAccount created = service.create(bankAccount);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created); //TODO in aa proper way
    }

    @PutMapping(value = "/{id}/deposit")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deposit(@PathVariable("id") int number, @RequestParam("sum") BigDecimal sum){
        BankAccount bankAccount = service.getByNumber(number);
        //assureIdConsistent(dish, id); /*TODO find place for logic + service transactional*/
        service.update(bankAccount,sum);
    }

    @PutMapping(value = "/{id}/withdraw")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void withdraw(@PathVariable("id") int number, @RequestParam("sum") BigDecimal sum){
        BankAccount bankAccount = service.getByNumber(number);
        //assureIdConsistent(dish, id); /*TODO find place for logic + service transactional*/
        service.update(bankAccount,sum);
    }

    @GetMapping(value = "/{id}/balance")
    public BigDecimal balance(@PathVariable("id") int number){
        return service.getByNumber(number).getBalance();
    }



}
