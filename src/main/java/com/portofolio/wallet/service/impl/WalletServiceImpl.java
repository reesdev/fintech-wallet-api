package com.portofolio.wallet.service.impl;

import com.portofolio.wallet.dto.request.DepositRequest;
import com.portofolio.wallet.dto.request.TransferRequest;
import com.portofolio.wallet.dto.response.DepositResponse;
import com.portofolio.wallet.dto.response.TransactionResponse;
import com.portofolio.wallet.dto.response.TransferResponse;
import com.portofolio.wallet.dto.response.WalletResponse;
import com.portofolio.wallet.entity.Transaction;
import com.portofolio.wallet.entity.User;
import com.portofolio.wallet.entity.Wallet;
import com.portofolio.wallet.exception.*;
import com.portofolio.wallet.repository.TransactionRepository;
import com.portofolio.wallet.repository.UserRepository;
import com.portofolio.wallet.repository.WalletRepository;
import com.portofolio.wallet.service.WalletService;
import com.portofolio.wallet.util.UUID.ReferenceUtil;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public WalletServiceImpl(WalletRepository walletRepository,
                             UserRepository userRepository,
                             TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public WalletResponse getMyWallet() {
        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(WalletNotFoundException::new);

        WalletResponse response = new WalletResponse();
        response.setBalance(wallet.getBalance());

        return response;
    }

    @Override
    @Transactional
    public DepositResponse deposit(DepositRequest request) {

        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(WalletNotFoundException::new);

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException();
        }

        wallet.setBalance(wallet.getBalance().add(request.getAmount()));
        walletRepository.save(wallet);

        Transaction transaction = new Transaction();
        transaction.setWallet(wallet);
        transaction.setAmount(request.getAmount());
        transaction.setType("DEPOSIT");
        transaction.setReferenceId(ReferenceUtil.generate());
        transaction.setDescription("Wallet Deposit");
        transaction.setCreatedAt(LocalDateTime.now());

        transactionRepository.save(transaction);

        DepositResponse response = new DepositResponse();
        response.setBalance(wallet.getBalance());

        return response;
    }

    @Override
    public Page<TransactionResponse> getMyTransactions(int page, int size) {

        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(WalletNotFoundException::new);

        Pageable pageable = PageRequest.of(page,size, Sort.by("createdAt").descending());
        Page<Transaction> transactions = transactionRepository.findByWallet(wallet,pageable);

        return transactions.map(tx -> {
            TransactionResponse response = new TransactionResponse();
            response.setType(tx.getType());
            response.setAmount(tx.getAmount());
            response.setCreatedAt(tx.getCreatedAt());
            return response;
        });
    }

    @Override
    @Transactional
    public TransferResponse transfer(TransferRequest request) {

        if (transactionRepository.existsByReferenceId(request.getReferenceId())) {
            throw new DuplicateTransactionException();
        }

        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        User sender = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        User receiver = userRepository.findById(request.getReceiverId())
                .orElseThrow(UserNotFoundException::new);

        if (sender.getId().equals(receiver.getId())) {
            throw new InvalidAmountException(); // or custom if you want later
        }

        Wallet senderWallet = walletRepository.findByUser(sender)
                .orElseThrow(WalletNotFoundException::new);

        Wallet receiverWallet = walletRepository.findByUser(receiver)
                .orElseThrow(WalletNotFoundException::new);

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException();
        }

        if (senderWallet.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientBalanceException();
        }

        senderWallet.setBalance(senderWallet.getBalance().subtract(request.getAmount()));
        receiverWallet.setBalance(receiverWallet.getBalance().add(request.getAmount()));

        walletRepository.save(senderWallet);
        walletRepository.save(receiverWallet);

        Transaction senderTx = new Transaction();
        senderTx.setWallet(senderWallet);
        senderTx.setAmount(request.getAmount());
        senderTx.setType("TRANSFER_OUT");
        senderTx.setReferenceId(request.getReferenceId());
        senderTx.setCreatedAt(LocalDateTime.now());

        Transaction receiverTx = new Transaction();
        receiverTx.setWallet(receiverWallet);
        receiverTx.setAmount(request.getAmount());
        receiverTx.setType("TRANSFER_IN");
        receiverTx.setReferenceId(request.getReferenceId());
        receiverTx.setCreatedAt(LocalDateTime.now());

        transactionRepository.save(senderTx);
        transactionRepository.save(receiverTx);

        TransferResponse response = new TransferResponse();
        response.setBalance(senderWallet.getBalance());

        return response;
    }
}