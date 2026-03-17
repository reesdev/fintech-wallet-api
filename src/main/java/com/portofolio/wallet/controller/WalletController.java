package com.portofolio.wallet.controller;

import com.portofolio.wallet.dto.request.DepositRequest;
import com.portofolio.wallet.dto.request.LoginRequest;
import com.portofolio.wallet.dto.request.TransferRequest;
import com.portofolio.wallet.dto.response.*;
import com.portofolio.wallet.service.WalletService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {
    private final WalletService walletService;
    public WalletController(WalletService walletService){

        this.walletService = walletService;
    }

    @GetMapping
    public CommonResponse<WalletResponse> getWallet(){
        WalletResponse data = walletService.getMyWallet();
        return CommonResponse.<WalletResponse>builder()
                .timestamp(LocalDateTime.now())
                .status("SUCCESS")
                .code("GET_WALLET_SUCCESS")
                .message("Wallet retrieved successfully")
                .data(data)
                .build();
    }

    @GetMapping("/transactions")
    public CommonResponse<List<TransactionResponse>> getTransactions(){
        List<TransactionResponse> data = walletService.getMyTransactions();
        return CommonResponse.<List<TransactionResponse>>builder()
                .timestamp(LocalDateTime.now())
                .status("SUCCESS")
                .code("GET_TRANSACTIONS_SUCCESS")
                .message("Transactions retrieved successfully")
                .data(data)
                .build();
    }

    @PostMapping("/deposit")
    public CommonResponse<DepositResponse> login(@RequestBody DepositRequest request){
        DepositResponse data = walletService.deposit(request);
        return CommonResponse.<DepositResponse>builder()
                .timestamp(LocalDateTime.now())
                .status("SUCCESS")
                .code("DEPOSIT_SUCCESS")
                .message("Deposit successful")
                .data(data)
                .build();
    }
    @PostMapping("/transfer")
    public CommonResponse<TransferResponse> login(@RequestBody TransferRequest request){
        TransferResponse data = walletService.transfer(request);
        return CommonResponse.<TransferResponse>builder()
                .timestamp(LocalDateTime.now())
                .status("SUCCESS")
                .code("TRANSFER_SUCCESS")
                .message("Transfer successful")
                .data(data)
                .build();
    }
}
