package com.yutian.multi_task_board_backend.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name="task")
public class Task {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="user_id")
    private int userId;


    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="label")
    private String label;

    @Column(name="due_date")
    private LocalDate dueDate;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name="`index`")
    private int index;

    // define constructors
    public Task(){

    }


    public Task(int userId, String title, String description, String label, LocalDate dueDate, TaskStatus status, int index) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.label = label;
        this.dueDate = dueDate;
        this.status = status;
        this.index = index;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", label='" + label + '\'' +
                ", dueDate=" + dueDate +
                ", status=" + status +
                ", index=" + index +
                '}';
    }
}
