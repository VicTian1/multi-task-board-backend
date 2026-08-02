package com.yutian.multi_task_board_backend.controller;


import com.yutian.multi_task_board_backend.dto.UpdateStatusRequest;
import com.yutian.multi_task_board_backend.entity.Task;
import com.yutian.multi_task_board_backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
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
    public Task getTask(@PathVariable int taskId){
        return taskService.getTaskById(taskId);
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task theTask){
        return taskService.createTask(theTask);
    }

    @PutMapping("/tasks/{taskId}")
    public Task updateTask(@PathVariable int taskId, @RequestBody Task theTask){
        return taskService.updateTask(taskId,theTask);
    }

    @PatchMapping("/tasks/{taskId}/status")
    public Task updateTaskStatus(@PathVariable int taskId, @RequestBody UpdateStatusRequest request){
        return taskService.updateTaskStatus(taskId,request.getStatus());
    }

    @DeleteMapping("/tasks/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable int taskId){
        taskService.deleteTaskById(taskId);
    }


}
