package com.yutian.multi_task_board_backend.service;

import com.yutian.multi_task_board_backend.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);
    Boolean existsByUsername(String username);
    User createUser(User user);
}
