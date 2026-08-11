package com.yutian.multi_task_board_backend.controller;


import com.yutian.multi_task_board_backend.dto.UserResponse;
import com.yutian.multi_task_board_backend.dto.JwtResponse;
import com.yutian.multi_task_board_backend.dto.UserLoginRequest;
import com.yutian.multi_task_board_backend.dto.UserRegisterRequest;
import com.yutian.multi_task_board_backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        return authService.register(userRegisterRequest);
    }

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody UserLoginRequest userLoginRequest){
        return authService.login(userLoginRequest);
    }


}


