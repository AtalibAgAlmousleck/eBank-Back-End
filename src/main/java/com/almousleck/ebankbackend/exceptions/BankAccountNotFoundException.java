package com.almousleck.ebankbackend.exceptions;

public class BankAccountNotFoundException extends Exception{

    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
