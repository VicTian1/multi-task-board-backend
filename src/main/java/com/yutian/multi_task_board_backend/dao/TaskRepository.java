package com.yutian.multi_task_board_backend.dao;


import com.yutian.multi_task_board_backend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Integer> {
}
