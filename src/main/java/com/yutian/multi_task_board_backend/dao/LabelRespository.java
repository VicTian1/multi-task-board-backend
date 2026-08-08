package com.yutian.multi_task_board_backend.dao;

import com.yutian.multi_task_board_backend.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRespository extends JpaRepository<Label, Integer> {
    boolean existsByType(String type);
}
