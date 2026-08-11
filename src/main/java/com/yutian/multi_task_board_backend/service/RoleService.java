package com.yutian.multi_task_board_backend.service;

import com.yutian.multi_task_board_backend.entity.Role;
import com.yutian.multi_task_board_backend.exception.RoleNotFoundException;

import java.util.Optional;

public interface RoleService {


    Role findByRolename(String Rolename) throws RoleNotFoundException;
}
