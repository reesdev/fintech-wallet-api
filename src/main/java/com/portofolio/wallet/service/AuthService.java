package com.portofolio.wallet.service;

import com.portofolio.wallet.dto.request.LoginRequest;
import com.portofolio.wallet.dto.request.RegisterRequest;
import com.portofolio.wallet.dto.response.LoginResponse;
import com.portofolio.wallet.dto.response.UserResponse;

public interface AuthService {

    UserResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}