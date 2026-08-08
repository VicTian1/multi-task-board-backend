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


    @Column(name="title")
    @NotBlank(message="Titile cannot be empty")
    @Size(max=100, message="Title cannot exceed 100 characters")
    private String title;

    @Column(name="description")
    @Size(max=500, message="Description cannot exceed 500 characters")
    private String description;

    @Column(name="label")
    private String label;

    @Column(name="due_date")
    @FutureOrPresent(message="Due date must be today or in the future")
    private LocalDate dueDate;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status cannot be null")
    private TaskStatus status;

    // define constructors
    public Task(){

    }


    public Task(String title, String description, String label, LocalDate dueDate, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.label = label;
        this.dueDate = dueDate;
        this.status = status;
    }


    // define setters and getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLabel() {
        return label;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }


    // define toString method
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", label='" + label + '\'' +
                ", dueDate=" + dueDate +
                ", status='" + status + '\'' +
                '}';
    }
}
