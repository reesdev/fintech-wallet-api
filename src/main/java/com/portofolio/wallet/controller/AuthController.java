package com.portofolio.wallet.controller;

import com.portofolio.wallet.dto.request.LoginRequest;
import com.portofolio.wallet.dto.request.RegisterRequest;
import com.portofolio.wallet.dto.response.CommonResponse;
import com.portofolio.wallet.dto.response.LoginResponse;
import com.portofolio.wallet.dto.response.UserResponse;
import com.portofolio.wallet.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public CommonResponse<UserResponse> register(@Valid @RequestBody RegisterRequest request){
        UserResponse data = authService.register(request);
        return CommonResponse.<UserResponse>builder()
                .timestamp(LocalDateTime.now())
                .status("SUCCESS")
                .code("REGISTER_SUCCESS")
                .message("Register successful")
                .data(data)
                .build();
    }

    @PostMapping("/login")
    public CommonResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        LoginResponse data = authService.login(request);

        return CommonResponse.<LoginResponse>builder()
                .timestamp(LocalDateTime.now())
                .status("SUCCESS")
                .code("LOGIN_SUCCESS")
                .message("Login successful")
                .data(data)
                .build();
    }
}