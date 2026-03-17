package com.portofolio.wallet.service;

import com.portofolio.wallet.dto.request.DepositRequest;
import com.portofolio.wallet.dto.request.TransferRequest;
import com.portofolio.wallet.dto.response.DepositResponse;
import com.portofolio.wallet.dto.response.TransactionResponse;
import com.portofolio.wallet.dto.response.TransferResponse;
import com.portofolio.wallet.dto.response.WalletResponse;
import org.springframework.data.domain.Page;


import java.util.List;

public interface WalletService {
    WalletResponse getMyWallet();
    DepositResponse deposit(DepositRequest request);
    Page<TransactionResponse> getMyTransactions(int page, int size);
    TransferResponse transfer(TransferRequest transferRequest);
}
