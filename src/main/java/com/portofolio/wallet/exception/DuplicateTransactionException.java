package com.portofolio.wallet.exception;

public class DuplicateTransactionException extends ApiException{
    public DuplicateTransactionException(){
        super("Duplicate transaction referenceId");
    }
}
