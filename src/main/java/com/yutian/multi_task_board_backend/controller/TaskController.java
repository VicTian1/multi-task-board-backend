package com.yutian.multi_task_board_backend.controller;


import com.yutian.multi_task_board_backend.dto.TaskCreateRequest;
import com.yutian.multi_task_board_backend.dto.TaskUpdateRequest;
import com.yutian.multi_task_board_backend.dto.UpdateStatusRequest;
import com.yutian.multi_task_board_backend.entity.Task;
import com.yutian.multi_task_board_backend.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public List<Task> getAllTasks(HttpServletRequest request){
        return taskService.getAllTasks((Integer)request.getAttribute("userId"));
    }

    @GetMapping("/tasks/{taskId}")
    public Task getTask(@Min(value=1,message="Id must be a positive integer") @PathVariable int taskId, HttpServletRequest request){
        return taskService.getTaskById(taskId,(Integer)request.getAttribute("userId"));
    }

    @PostMapping("/tasks")
    public Task createTask(@Valid @RequestBody TaskCreateRequest theTaskCreateRequest,HttpServletRequest request){
        return taskService.createTask(theTaskCreateRequest,(Integer)request.getAttribute("userId"));
    }

    @PutMapping("/tasks/{taskId}")
    public Task updateTask(@Min(value=1,message="Id must be a positive integer") @PathVariable int taskId, HttpServletRequest request,@Valid @RequestBody TaskUpdateRequest theTaskUpdateRequest){
        return taskService.updateTask(taskId,(Integer)request.getAttribute("userId"),theTaskUpdateRequest);
    }

    @PatchMapping("/tasks/{taskId}/status")
    public Task updateTaskStatus(@Min(value=1,message="Id must be a positive integer") @PathVariable int taskId, HttpServletRequest request,@Valid @RequestBody UpdateStatusRequest statusRequest){
        return taskService.updateTaskStatus(taskId,(Integer)request.getAttribute("userId"),statusRequest.getStatus());
    }

    @DeleteMapping("/tasks/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@Min(value=1,message="Id must be a positive integer") @PathVariable int taskId,HttpServletRequest request){
        taskService.deleteTaskById(taskId,(Integer)request.getAttribute("userId"));
    }


}
