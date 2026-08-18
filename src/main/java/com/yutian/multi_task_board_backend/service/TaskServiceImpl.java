package com.yutian.multi_task_board_backend.service;


import com.yutian.multi_task_board_backend.dao.TaskRepository;
import com.yutian.multi_task_board_backend.dto.TaskCreateRequest;
import com.yutian.multi_task_board_backend.dto.TaskUpdateRequest;
import com.yutian.multi_task_board_backend.entity.Task;
import com.yutian.multi_task_board_backend.entity.TaskStatus;
import com.yutian.multi_task_board_backend.exception.TaskNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final LabelService labelService;

    public TaskServiceImpl(TaskRepository taskRepository,LabelService labelService){
        this.taskRepository=taskRepository;
        this.labelService=labelService;

    }


    @Override
    public List<Task> getAllTasks(int userId) {
        return taskRepository.findByUserId(userId);
    }

    @Override
    public Task getTaskById(int theId,int userId) {
        Optional<Task> result = taskRepository.findByIdAndUserId(theId,userId);
        return result.orElseThrow(()-> new TaskNotFoundException("Task id not found-"+theId));

    }



    private Boolean validateLabel(Task theTask){

        if(theTask.getLabel()==null || (labelService.existByType((theTask.getLabel())))){
            return true;
        }else{
            throw new IllegalArgumentException("Label Type "+theTask.getLabel()+" does not exist");
        }

    }

    private String trimToNull(String str){
        if(str==null || str.trim().isEmpty()){
            return null;
        }
        return str.trim();
    }

    private void preHandle(Task theTask) {
        if(theTask.getTitle()!=null){
            theTask.setTitle(theTask.getTitle().trim());
        }

        theTask.setDescription(trimToNull(theTask.getDescription()));
        theTask.setLabel(trimToNull(theTask.getLabel()));
        if(theTask.getLabel()!=null){
            theTask.setLabel(theTask.getLabel().toLowerCase());
        }
    }

    @Override
    public Task createTask(TaskCreateRequest theTaskCreateRequest,int userId) {
        Task theTask = new Task();
        theTask.setTitle(theTaskCreateRequest.getTitle());
        theTask.setDescription(theTaskCreateRequest.getDescription());
        theTask.setLabel(theTaskCreateRequest.getLabel());
        theTask.setStatus(theTaskCreateRequest.getStatus());
        theTask.setDueDate(theTaskCreateRequest.getDueDate());
        preHandle(theTask);
        validateLabel(theTask);
        theTask.setUserId(userId);
        theTask.setId(0);
        return taskRepository.save(theTask);
    }

    @Override
    public Task updateTask(int theId,int userId,TaskUpdateRequest theTaskUpdateRequest) {
        Task tempTask=getTaskById(theId,userId);

        if(theTaskUpdateRequest.getDueDate()!=null && !theTaskUpdateRequest.getDueDate().equals(tempTask.getDueDate())) {
            if(theTaskUpdateRequest.getDueDate().isBefore(LocalDate.now())){
                throw new IllegalArgumentException("Due date must be today or in the future");
            }
        }

        Task theTask=new Task();
        theTask.setTitle(theTaskUpdateRequest.getTitle());
        theTask.setDescription(theTaskUpdateRequest.getDescription());
        theTask.setLabel(theTaskUpdateRequest.getLabel());
        theTask.setDueDate(theTaskUpdateRequest.getDueDate());
        theTask.setStatus(theTaskUpdateRequest.getStatus());
        preHandle(theTask);
        validateLabel(theTask);
        theTask.setUserId(userId);
        theTask.setId(theId);
        return taskRepository.save(theTask);
    }

    @Override
    public Task updateTaskStatus(int theId, int userId,TaskStatus status) {
        Task task=getTaskById(theId,userId);
        task.setStatus(status);
        return taskRepository.save(task);

    }

    @Override
    public void deleteTaskById(int theId,int userId) {
        Task task=getTaskById(theId,userId);
        taskRepository.delete(task);

    }
}
