package com.yutian.multi_task_board_backend.dto;

import com.yutian.multi_task_board_backend.entity.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TaskCreateRequest {


    @NotBlank(message="Title cannot be empty")
    @Size(max=100, message="Title cannot exceed 100 characters")
    private String title;

    @Size(max=500, message="Description cannot exceed 500 characters")
    private String description;

    private String label;

    @FutureOrPresent(message="Due date must be today or in the future")
    private LocalDate dueDate;

    @NotNull(message = "Status cannot be null")
    private TaskStatus status;

    public TaskCreateRequest(){

    }

    public TaskCreateRequest(String title, String description, String label, LocalDate dueDate, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.label = label;
        this.dueDate = dueDate;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaskCreateRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", label='" + label + '\'' +
                ", dueDate=" + dueDate +
                ", status=" + status +
                '}';
    }
}
