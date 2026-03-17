package com.portofolio.wallet.service.impl;

import com.portofolio.wallet.dto.response.UserResponse;
import com.portofolio.wallet.entity.User;
import com.portofolio.wallet.exception.UserNotFoundException;
import com.portofolio.wallet.repository.UserRepository;
import com.portofolio.wallet.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public UserResponse getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        return mapToResponse(user);
    }

    private UserResponse mapToResponse(User user){
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        response.setStatus(user.getStatus());
        return response;
    }
}