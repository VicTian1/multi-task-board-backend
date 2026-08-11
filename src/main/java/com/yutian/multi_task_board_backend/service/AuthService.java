package com.yutian.multi_task_board_backend.service;


import com.yutian.multi_task_board_backend.dto.JwtResponse;
import com.yutian.multi_task_board_backend.dto.UserLoginRequest;
import com.yutian.multi_task_board_backend.dto.UserResponse;
import com.yutian.multi_task_board_backend.dto.UserRegisterRequest;

public interface AuthService {

    UserResponse register(UserRegisterRequest userRegisterRequest);
    JwtResponse login(UserLoginRequest userLoginRequest);
}
