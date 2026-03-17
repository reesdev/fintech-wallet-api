package com.portofolio.wallet.exception;

public class InvalidTransferException extends ApiException{
    public InvalidTransferException(String message){
        super(message);
    }
}
