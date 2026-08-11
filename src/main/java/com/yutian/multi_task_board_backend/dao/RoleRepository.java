package com.yutian.multi_task_board_backend.dao;

import com.yutian.multi_task_board_backend.entity.Role;
import com.yutian.multi_task_board_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findByName(String name);
}
