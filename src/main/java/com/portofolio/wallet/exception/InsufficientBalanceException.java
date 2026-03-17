package com.portofolio.wallet.exception;

public class InsufficientBalanceException extends ApiException{
    public InsufficientBalanceException(){
        super("Insufficient Balance");
    }
}
