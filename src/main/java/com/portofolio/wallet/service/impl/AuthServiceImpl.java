package com.portofolio.wallet.service.impl;

import com.portofolio.wallet.dto.request.RegisterRequest;
import com.portofolio.wallet.dto.response.UserResponse;
import com.portofolio.wallet.repository.UserRepository;
import com.portofolio.wallet.repository.WalletRepository;
import com.portofolio.wallet.service.AuthService;
import org.springframework.stereotype.Service;
import com.portofolio.wallet.entity.User;
import com.portofolio.wallet.entity.Wallet;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
        if (userRepository.existsByEmail((request.getEmail()))){
            throw new RuntimeException("Email Already Registered");
        }
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .fullName(request.getFullName())
                .status("ACTIVE")
                .createdAt(LocalDateTime.now())
                .build();
        userRepository.save(user);

        Wallet wallet = Wallet.builder()
                .user(user)
                .balance(BigDecimal.ZERO)
                .createdAt(LocalDateTime.now())
                .build();
        walletRepository.save(wallet);

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        response.setStatus(user.getStatus());
        return response;
    }

}
