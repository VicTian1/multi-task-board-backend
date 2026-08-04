package com.yutian.multi_task_board_backend.dao;

import com.yutian.multi_task_board_backend.entity.User;

public interface UserDao {

    User findByUserName(String userName);
}
