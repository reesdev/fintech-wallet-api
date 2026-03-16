package com.portofolio.wallet.service.impl;

import com.portofolio.wallet.dto.request.RegisterRequest;
import com.portofolio.wallet.dto.response.UserResponse;
import com.portofolio.wallet.repository.UserRepository;
import com.portofolio.wallet.repository.WalletRepository;
import com.portofolio.wallet.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    public AuthServiceImpl(UserRepository userRepository,
                           WalletRepository walletRepository){
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public UserResponse register(RegisterRequest request){
        return null;
    }

}
