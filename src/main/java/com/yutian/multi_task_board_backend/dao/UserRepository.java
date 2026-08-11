package com.yutian.multi_task_board_backend.dao;

import com.yutian.multi_task_board_backend.entity.Task;
import com.yutian.multi_task_board_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
