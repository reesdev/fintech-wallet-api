package com.portofolio.wallet.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("User Not Found");
    }
}
