package com.yutian.multi_task_board_backend.controller;

import com.yutian.multi_task_board_backend.entity.Label;
import com.yutian.multi_task_board_backend.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LabelController {

    private final LabelService labelService;

    @Autowired
    public LabelController(LabelService labelService){
        this.labelService=labelService;
    }

    @GetMapping("/labels")
    public List<Label> getAllLabels(){
        return labelService.getAllLabels();

    }
}
