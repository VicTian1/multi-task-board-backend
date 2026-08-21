package com.yutian.multi_task_board_backend.service;

import com.yutian.multi_task_board_backend.dto.TaskCreateRequest;
import com.yutian.multi_task_board_backend.dto.TaskUpdateRequest;
import com.yutian.multi_task_board_backend.dto.UpdateMoveRequest;
import com.yutian.multi_task_board_backend.entity.Task;
import com.yutian.multi_task_board_backend.entity.TaskStatus;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks(int userId);
    Task getTaskById(int theId,int userId);
    Task createTask(TaskCreateRequest theTaskCreateRequest,int userId);
    Task updateTask(int theId, int userId,TaskUpdateRequest theTaskUpdateRequest);
    Task updateTaskStatus(int theId, int userId,TaskStatus status);
    void deleteTaskById(int theId,int userId);
    Task updateTaskStatusAndIndex(int theId, int userId, UpdateMoveRequest moveRequest);


}
