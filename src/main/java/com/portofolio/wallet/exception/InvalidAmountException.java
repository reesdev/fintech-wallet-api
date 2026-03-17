package com.portofolio.wallet.exception;

public class InvalidAmountException extends ApiException{
    public InvalidAmountException(){
        super("Amount must be greater than Zero(0)");
    }
}
