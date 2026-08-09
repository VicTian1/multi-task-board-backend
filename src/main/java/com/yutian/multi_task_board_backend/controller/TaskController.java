package com.yutian.multi_task_board_backend.controller;


import com.yutian.multi_task_board_backend.dto.TaskCreateRequest;
import com.yutian.multi_task_board_backend.dto.TaskUpdateRequest;
import com.yutian.multi_task_board_backend.dto.UpdateStatusRequest;
import com.yutian.multi_task_board_backend.entity.Task;
import com.yutian.multi_task_board_backend.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService theTaskService){
        this.taskService=theTaskService;
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/{taskId}")
    public Task getTask(@Min(value=1,message="Id must be a positive integer") @PathVariable int taskId){
        return taskService.getTaskById(taskId);
    }

    @PostMapping("/tasks")
    public Task createTask(@Valid @RequestBody TaskCreateRequest theTaskCreateRequest){
        return taskService.createTask(theTaskCreateRequest);
    }

    @PutMapping("/tasks/{taskId}")
    public Task updateTask(@Min(value=1,message="Id must be a positive integer") @PathVariable int taskId, @Valid @RequestBody TaskUpdateRequest theTaskUpdateRequest){
        return taskService.updateTask(taskId,theTaskUpdateRequest);
    }

    @PatchMapping("/tasks/{taskId}/status")
    public Task updateTaskStatus(@Min(value=1,message="Id must be a positive integer") @PathVariable int taskId, @Valid @RequestBody UpdateStatusRequest request){
        return taskService.updateTaskStatus(taskId,request.getStatus());
    }

    @DeleteMapping("/tasks/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@Min(value=1,message="Id must be a positive integer") @PathVariable int taskId){
        taskService.deleteTaskById(taskId);
    }


}
