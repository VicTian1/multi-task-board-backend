package com.yutian.multi_task_board_backend.dto;

import com.yutian.multi_task_board_backend.entity.TaskStatus;
import jakarta.validation.constraints.NotNull;

public class UpdateStatusRequest {

    @NotNull(message = "Status cannot be null")
    private TaskStatus status;

    public UpdateStatusRequest() {
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
