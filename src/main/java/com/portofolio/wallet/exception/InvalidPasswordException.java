package com.portofolio.wallet.exception;

public class InvalidPasswordException extends ApiException{
    public InvalidPasswordException(){
        super("Invalid Password");
    }
}
