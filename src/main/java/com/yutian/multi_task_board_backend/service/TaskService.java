package com.yutian.multi_task_board_backend.service;

import com.yutian.multi_task_board_backend.dto.TaskCreateRequest;
import com.yutian.multi_task_board_backend.dto.TaskUpdateRequest;
import com.yutian.multi_task_board_backend.entity.Task;
import com.yutian.multi_task_board_backend.entity.TaskStatus;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(int theId);
    Task createTask(TaskCreateRequest theTaskCreateRequest);
    Task updateTask(int theId, TaskUpdateRequest theTaskUpdateRequest);
    Task updateTaskStatus(int theId, TaskStatus status);
    void deleteTaskById(int theId);


}
