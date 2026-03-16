package com.portofolio.wallet.service.impl;

import com.portofolio.wallet.dto.request.DepositRequest;
import com.portofolio.wallet.dto.response.DepositResponse;
import com.portofolio.wallet.dto.response.TransactionResponse;
import com.portofolio.wallet.dto.response.WalletResponse;
import com.portofolio.wallet.entity.Transaction;
import com.portofolio.wallet.entity.User;
import com.portofolio.wallet.entity.Wallet;
import com.portofolio.wallet.repository.TransactionRepository;
import com.portofolio.wallet.repository.UserRepository;
import com.portofolio.wallet.repository.WalletRepository;
import com.portofolio.wallet.service.WalletService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    public WalletServiceImpl(WalletRepository walletRepository, UserRepository userRepository, TransactionRepository transactionRepository){
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }
    @Override
    public WalletResponse getMyWallet(){
        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->new RuntimeException("User Not Found"));
        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(()-> new RuntimeException("Wallet Not Found"));
        WalletResponse response = new WalletResponse();
        response.setBalance(wallet.getBalance());
        return response;
    }
    @Override
    public DepositResponse deposit(DepositRequest request){
        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->new RuntimeException("User Not Found"));
        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(()-> new RuntimeException("Wallet Not Found"));
        if(request.getAmount().compareTo(BigDecimal.ZERO)<=0){
            throw new RuntimeException("Amount must be greater than zero");
        }
        wallet.setBalance(wallet.getBalance().add(request.getAmount()));
        walletRepository.save(wallet);

        Transaction transaction = new Transaction();
        transaction.setWallet(wallet);
        transaction.setAmount(request.getAmount());
        transaction.setType("DEPOSIT");
        transaction.setReferenceId(UUID.randomUUID().toString());
        transaction.setDescription("Wallet Deposit");
        transaction.setCreatedAt(LocalDateTime.now());

        transactionRepository.save(transaction);

        DepositResponse response = new DepositResponse();
        response.setBalance(wallet.getBalance());
        return response;
    }
    @Override
    public List<TransactionResponse> getMyTransactions(){
        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->new RuntimeException("User Not Found"));
        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(()-> new RuntimeException("Wallet Not Found"));
        List<Transaction> transactions = transactionRepository.findByWallet(wallet);
        return transactions.stream().map(tx -> {
            TransactionResponse response = new TransactionResponse();
            response.setType(tx.getType());
            response.setAmount(tx.getAmount());
            response.setCreatedAt(tx.getCreatedAt());
            return response;
        }).toList();
    }

}
