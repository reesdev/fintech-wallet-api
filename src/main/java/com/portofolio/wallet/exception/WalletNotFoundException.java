package com.portofolio.wallet.exception;

public class WalletNotFoundException extends ApiException{
    public  WalletNotFoundException(){
        super("Wallet not found");
    }
}
