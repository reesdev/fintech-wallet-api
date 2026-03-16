package com.portofolio.wallet.controller;

import com.portofolio.wallet.dto.request.DepositRequest;
import com.portofolio.wallet.dto.response.DepositResponse;
import com.portofolio.wallet.dto.response.WalletResponse;
import com.portofolio.wallet.service.WalletService;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/deposit")
    public DepositResponse deposit(@RequestBody DepositRequest request){
        return walletService.deposit(request);
    }
}
