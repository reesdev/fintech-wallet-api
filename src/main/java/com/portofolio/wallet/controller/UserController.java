package com.portofolio.wallet.controller;

import com.portofolio.wallet.dto.response.CommonResponse;
import com.portofolio.wallet.dto.response.UserResponse;
import com.portofolio.wallet.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public CommonResponse<List<UserResponse>> getAllUsers(){
        List<UserResponse> data = userService.getAllUsers();
        return CommonResponse.<List<UserResponse>>builder()
                .timestamp(LocalDateTime.now())
                .status("SUCCESS")
                .code("GET_USERS_SUCCESS")
                .message("Users retrieved successfully")
                .data(data)
                .build();

    }@GetMapping("/{id}")
    public CommonResponse<UserResponse> getUserById(@PathVariable Long id){
        UserResponse data = userService.getUserById(id);

        return CommonResponse.<UserResponse>builder()
                .timestamp(LocalDateTime.now())
                .status("SUCCESS")
                .code("GET_USER_SUCCESS")
                .message("User retrieved successfully")
                .data(data)
                .build();

    }

}
