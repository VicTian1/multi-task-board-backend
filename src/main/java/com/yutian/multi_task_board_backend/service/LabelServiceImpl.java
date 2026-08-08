package com.yutian.multi_task_board_backend.service;

import com.yutian.multi_task_board_backend.dao.LabelRespository;
import com.yutian.multi_task_board_backend.entity.Label;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {

    private final LabelRespository labelRespository;

    public LabelServiceImpl(LabelRespository labelRespository){
        this.labelRespository=labelRespository;
    }

    @Override
    public List<Label> getAllLabels() {
        return labelRespository.findAll();
    }

    @Override
    public boolean existByType(String type) {
        return labelRespository.existsByType(type);
    }
}
