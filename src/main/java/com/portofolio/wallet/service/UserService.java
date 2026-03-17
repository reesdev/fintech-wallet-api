package com.portofolio.wallet.service;

import com.portofolio.wallet.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
}
