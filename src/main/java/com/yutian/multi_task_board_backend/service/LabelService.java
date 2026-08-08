package com.yutian.multi_task_board_backend.service;

import com.yutian.multi_task_board_backend.entity.Label;

import java.util.List;

public interface LabelService {
    List<Label> getAllLabels();
    boolean existByType(String type);
}
