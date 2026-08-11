package com.yutian.multi_task_board_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserRegisterRequest {



    @NotBlank(message = "username cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,16}$",message="Username must be 4-16 characters long and contain only contain letters,numbers, and underscores")
    private String username;

    @NotBlank(message = "password cannot be empty")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,32}$",message="Password must be 8-32 characters long and contain both letters and numbers")
    private String password;

    public UserRegisterRequest(){

    }

    public UserRegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserCreateRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
