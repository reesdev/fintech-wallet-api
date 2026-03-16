package com.portofolio.wallet.service;

import com.portofolio.wallet.dto.request.DepositRequest;
import com.portofolio.wallet.dto.response.DepositResponse;
import com.portofolio.wallet.dto.response.TransactionResponse;
import com.portofolio.wallet.dto.response.WalletResponse;

import java.util.List;

public interface WalletService {
    WalletResponse getMyWallet();
    DepositResponse deposit(DepositRequest request);
    List<TransactionResponse> getMyTransactions();
}
