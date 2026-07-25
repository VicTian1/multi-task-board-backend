package com.yutian.multi_task_board_backend.controller;


import com.yutian.multi_task_board_backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService theTaskService){
        taskService=theTaskService;
    }

    @GetMapping("/")
    public String home(){
        return taskService.hello();
    }
}
