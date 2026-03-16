package com.portofolio.wallet.controller;

import com.portofolio.wallet.dto.response.WalletResponse;
import com.portofolio.wallet.service.WalletService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {
    private final WalletService walletService;
    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }
    @GetMapping
    public WalletResponse getMyWallet(){
        return walletService.getMyWallet();
    }
}
