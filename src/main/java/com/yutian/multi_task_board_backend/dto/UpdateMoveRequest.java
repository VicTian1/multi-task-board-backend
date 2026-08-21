package com.yutian.multi_task_board_backend.dto;

import com.yutian.multi_task_board_backend.entity.TaskStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class UpdateMoveRequest {


    private TaskStatus status;

    @Min(value=0,message="Index must be non-negative")
    private int index;

    public UpdateMoveRequest(){

    }

    public UpdateMoveRequest(TaskStatus status, int index) {
        this.status = status;
        this.index = index;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
