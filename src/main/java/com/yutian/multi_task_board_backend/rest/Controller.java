package com.yutian.multi_task_board_backend.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public String hello(){
        return "Spring boot is running.";
    }
}
