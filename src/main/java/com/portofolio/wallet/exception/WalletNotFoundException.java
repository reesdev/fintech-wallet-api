package com.portofolio.wallet.exception;

public class WalletNotFoundException extends RuntimeException{
    public  WalletNotFoundException(){
        super("Wallet not found");
    }
}
