package com.portofolio.wallet.service.impl;

import com.portofolio.wallet.dto.request.LoginRequest;
import com.portofolio.wallet.dto.request.RegisterRequest;
import com.portofolio.wallet.dto.response.LoginResponse;
import com.portofolio.wallet.dto.response.UserResponse;
import com.portofolio.wallet.exception.EmailAlreadyRegisteredException;
import com.portofolio.wallet.exception.InvalidPasswordException;
import com.portofolio.wallet.exception.UserNotFoundException;
import com.portofolio.wallet.repository.UserRepository;
import com.portofolio.wallet.repository.WalletRepository;
import com.portofolio.wallet.security.JwtService;
import com.portofolio.wallet.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.portofolio.wallet.entity.User;
import com.portofolio.wallet.entity.Wallet;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    public AuthServiceImpl(UserRepository userRepository,
                           WalletRepository walletRepository, PasswordEncoder passwordEncoder,
                           JwtService jwtService){
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public UserResponse register(RegisterRequest request){
        log.info("Register request: email={}", request.getEmail());
        if (userRepository.existsByEmail((request.getEmail()))){
            throw new EmailAlreadyRegisteredException();
        }
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
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
        log.info("Register request: email={}", request.getEmail());
        return response;
    }

    @Override
    public LoginResponse login(LoginRequest request){
        log.info("Login attempt: email={}", request.getEmail());
        User user = userRepository.findByEmail((request.getEmail()))
                .orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            log.error("Login failed: invalid password for email={}", request.getEmail());
            throw new InvalidPasswordException();
        }
        String token = jwtService.generateToken(user.getEmail());
        LoginResponse response = new LoginResponse();
        response.setAccessToken(token);
        log.info("Login success: email={}", request.getEmail());
        return  response;
    }

}
