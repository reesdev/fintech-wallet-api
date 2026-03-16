package com.portofolio.wallet.controller;

import com.portofolio.wallet.dto.request.LoginRequest;
import com.portofolio.wallet.dto.request.RegisterRequest;
import com.portofolio.wallet.dto.response.LoginResponse;
import com.portofolio.wallet.dto.response.UserResponse;
import com.portofolio.wallet.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest request){
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        return authService.login(request);
    }
}