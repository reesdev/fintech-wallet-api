package com.portofolio.wallet.service.impl;

import com.portofolio.wallet.dto.response.WalletResponse;
import com.portofolio.wallet.entity.User;
import com.portofolio.wallet.entity.Wallet;
import com.portofolio.wallet.repository.UserRepository;
import com.portofolio.wallet.repository.WalletRepository;
import com.portofolio.wallet.service.WalletService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    public WalletServiceImpl(WalletRepository walletRepository,UserRepository userRepository){
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
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
}
