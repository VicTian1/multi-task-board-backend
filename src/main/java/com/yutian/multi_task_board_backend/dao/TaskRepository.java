package com.yutian.multi_task_board_backend.dao;


import com.yutian.multi_task_board_backend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Integer> {

    List<Task> findByUserId(int userId);
    Optional<Task> findByIdAndUserId(int id,int userId);
}
