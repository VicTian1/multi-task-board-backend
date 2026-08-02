package com.yutian.multi_task_board_backend.service;


import com.yutian.multi_task_board_backend.dao.TaskRepository;
import com.yutian.multi_task_board_backend.entity.Task;
import com.yutian.multi_task_board_backend.entity.TaskStatus;
import com.yutian.multi_task_board_backend.exception.TaskNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;


    public TaskServiceImpl(TaskRepository theTaskRepository){
        taskRepository=theTaskRepository;

    }


    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(int theId) {
        Optional<Task> result = taskRepository.findById(theId);
        return result.orElseThrow(()-> new TaskNotFoundException("Task id not found-"+theId));

    }

    @Override
    public Task createTask(Task theTask) {
        theTask.setId(0);
        return taskRepository.save(theTask);
    }

    @Override
    public Task updateTask(int theId, Task theTask) {
        getTaskById(theId);
        theTask.setId(theId);
        return taskRepository.save(theTask);
    }

    @Override
    public Task updateTaskStatus(int theId, TaskStatus status) {
        Task task=getTaskById(theId);
        if(status==null ){
            throw new IllegalArgumentException("Illegal argument: "+status);
        }
        task.setStatus(status);
        return taskRepository.save(task);

    }

    @Override
    public void deleteTaskById(int theId) {
        Task task=getTaskById(theId);
        taskRepository.delete(task);

    }
}
