package com.example.bankaccount.util.exception;

public class NotEnoughBalanceException  extends RuntimeException  {
    public NotEnoughBalanceException(String arg) {
        super(arg);
    }
}
