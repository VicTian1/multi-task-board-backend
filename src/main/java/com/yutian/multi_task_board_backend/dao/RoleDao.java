package com.yutian.multi_task_board_backend.dao;

import com.yutian.multi_task_board_backend.entity.Role;

public interface RoleDao {

    Role findByRoleName(String roleName);
}
