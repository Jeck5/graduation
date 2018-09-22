package com.example.bankaccount.web;

import com.example.bankaccount.service.BankAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(BankAccountRestController.class)
public class BankAccountRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankAccountService service;

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
        mockMvc.perform(get("/bankaccount/1000/balance")).andDo(print());
    }
}