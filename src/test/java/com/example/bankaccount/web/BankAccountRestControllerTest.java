package com.example.bankaccount.web;

import com.example.bankaccount.model.BankAccount;
import com.example.bankaccount.service.BankAccountService;
import com.example.bankaccount.util.AccountAction;
import com.example.bankaccount.util.exception.NotEnoughBalanceException;
import com.example.bankaccount.util.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankAccountRestControllerTest {

    private static final int ACCOUNT_NUMBER = 11000;
    private static final int ACCOUNT_ID = 100001;
    private static final int ACCOUNT_NUMBER_BIG = 111000;
    private static final int ACCOUNT_NUMBER_SMALL = 1100;
    private static final BigDecimal SUM1 = BigDecimal.valueOf(500);
    private static final BigDecimal SUM2 = BigDecimal.valueOf(50.5);
    private static final BigDecimal SUM_NEGATIVE = BigDecimal.valueOf(-50.5);
    private static final BigDecimal SUM_THREE_FRACTION_DIGITS = BigDecimal.valueOf(5.255);

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @Autowired
    ObjectMapper objectMapper;

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
    public void create() throws Exception {
        BankAccount bankAccount1 = new BankAccount(ACCOUNT_NUMBER, SUM1);
        BankAccount bankAccount2 = new BankAccount(ACCOUNT_NUMBER, SUM1);
        bankAccount2.setId(ACCOUNT_ID);
        when(serviceMock.create(bankAccount1)).thenReturn(bankAccount2);
        mockMvc.perform(post(String.format("/bankaccount/%d", ACCOUNT_NUMBER)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(bankAccount2)));
    }

    @Test(expected = Exception.class)
    public void createWithSmallNumber() throws Exception {
        mockMvc.perform(post(String.format("/bankaccount/%d", ACCOUNT_NUMBER_SMALL)))
                .andDo(print());
    }

    @Test(expected = Exception.class)
    public void createWithBigNumber() throws Exception {
        mockMvc.perform(post(String.format("/bankaccount/%d", ACCOUNT_NUMBER_BIG)))
                .andDo(print());
    }

    @Test(expected = Exception.class)
    public void createDuplicated() throws Exception {
        BankAccount bankAccount1 = new BankAccount(ACCOUNT_NUMBER, SUM1);
        when(serviceMock.create(bankAccount1)).thenThrow(new Exception());
        mockMvc.perform(post(String.format("/bankaccount/%d", ACCOUNT_NUMBER_BIG)))
                .andDo(print());
    }

    @Test
    public void deposit() throws Exception {
        mockMvc.perform(put(String.format("/bankaccount/%d/deposit", ACCOUNT_NUMBER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(SUM1.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(serviceMock, times(1)).operate(ACCOUNT_NUMBER, SUM1, AccountAction.DEPOSIT);
    }

    @Test(expected = Exception.class)
    public void depositNegative() throws Exception {
        mockMvc.perform(put(String.format("/bankaccount/%d/deposit", ACCOUNT_NUMBER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(SUM_NEGATIVE.toString()))
                .andDo(print());
    }

    @Test(expected = Exception.class)
    public void depositThreeFractionDigits() throws Exception {
        mockMvc.perform(put(String.format("/bankaccount/%d/deposit", ACCOUNT_NUMBER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(SUM_THREE_FRACTION_DIGITS.toString()))
                .andDo(print());
    }

    @Test
    public void withdraw() throws Exception {
        mockMvc.perform(put(String.format("/bankaccount/%d/withdraw", ACCOUNT_NUMBER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(SUM1.toString()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(serviceMock, times(1)).operate(ACCOUNT_NUMBER, SUM1, AccountAction.WITHDRAW);
    }

    @Test(expected = Exception.class)
    public void withdrawNotEnough() throws Exception {
        doThrow(new NotEnoughBalanceException("Not enough balance")).when(serviceMock).operate(ACCOUNT_NUMBER,SUM1,AccountAction.WITHDRAW);
        mockMvc.perform(put(String.format("/bankaccount/%d/withdraw", ACCOUNT_NUMBER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(SUM1.toString()))
                .andDo(print());
    }


    @Test
    public void balance() throws Exception {
        when(serviceMock.getByAccountNumber(ACCOUNT_NUMBER)).thenReturn(new BankAccount(ACCOUNT_NUMBER, SUM1));
        mockMvc.perform(get(String.format("/bankaccount/%d/balance", ACCOUNT_NUMBER)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(SUM1.toString()));
    }

    @Test(expected = Exception.class)
    public void balanceNotFound() throws Exception {
        when(serviceMock.getByAccountNumber(ACCOUNT_NUMBER)).thenThrow(new NotFoundException("Not found"));
        mockMvc.perform(get(String.format("/bankaccount/%d/balance", ACCOUNT_NUMBER))).andDo(print());
    }
}