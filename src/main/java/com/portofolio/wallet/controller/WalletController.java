package com.portofolio.wallet.controller;

import com.portofolio.wallet.dto.request.DepositRequest;
import com.portofolio.wallet.dto.request.TransferRequest;
import com.portofolio.wallet.dto.response.DepositResponse;
import com.portofolio.wallet.dto.response.TransactionResponse;
import com.portofolio.wallet.dto.response.TransferResponse;
import com.portofolio.wallet.dto.response.WalletResponse;
import com.portofolio.wallet.service.WalletService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/transactions")
    public List<TransactionResponse> getTransactions(){
        return walletService.getMyTransactions();
    }
    @PostMapping("/transfer")
    public TransferResponse transfer(@RequestBody TransferRequest request){
        System.out.println("HIT TRANSFER");
        return walletService.transfer((request));
    }
}
