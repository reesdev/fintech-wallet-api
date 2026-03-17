package com.portofolio.wallet.exception;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(){
        super("Insufficient Balance");
    }
}
