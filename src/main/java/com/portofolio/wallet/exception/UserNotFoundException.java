package com.portofolio.wallet.exception;

public class UserNotFoundException extends ApiException{
    public UserNotFoundException(){
        super("User Not Found");
    }
}
