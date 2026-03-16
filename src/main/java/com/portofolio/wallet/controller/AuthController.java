package com.portofolio.wallet.controller;

import com.portofolio.wallet.dto.request.RegisterRequest;
import com.portofolio.wallet.dto.response.UserResponse;
import com.portofolio.wallet.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService){
        this.authService = authService;
    }
    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest request){
        return authService.register(request);
    }
}

