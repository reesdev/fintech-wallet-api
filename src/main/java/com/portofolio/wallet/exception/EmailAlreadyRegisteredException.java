package com.portofolio.wallet.exception;

public class EmailAlreadyRegisteredException extends ApiException{
    public EmailAlreadyRegisteredException(){
        super("Email Already Registered");
    }
}
