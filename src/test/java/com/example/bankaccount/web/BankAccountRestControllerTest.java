package com.example.bankaccount.web;

import com.example.bankaccount.model.BankAccount;
import com.example.bankaccount.service.BankAccountService;
import com.example.bankaccount.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
//@WebMvcTest(controllers = BankAccountRestController.class)
@SpringBootTest
public class BankAccountRestControllerTest {

    //@Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @MockBean
    private BankAccountService serviceMock;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void createWithLocation() {
    }

    @Test
    public void deposit() {
    }

    @Test
    public void withdraw() {
    }

    @Test
    public void balance() throws Exception {
        when(serviceMock.getById(1000)).thenReturn(new BankAccount(1000, BigDecimal.valueOf(500)));
        mockMvc.perform(get("/bankaccount/1000/balance")).andDo(print());
    }

    @Test(expected = NotFoundException.class) /*TODO relize why nested servlet*/
    public void balanceNotFound() throws Exception {
        when(serviceMock.getById(1000)).thenThrow(new NotFoundException("Not found"));
        mockMvc.perform(get("/bankaccount/1000/balance")).andDo(print());
    }
}