package com.yutian.multi_task_board_backend.service;


import com.yutian.multi_task_board_backend.dao.TaskRepository;
import com.yutian.multi_task_board_backend.dto.TaskCreateRequest;
import com.yutian.multi_task_board_backend.dto.TaskUpdateRequest;
import com.yutian.multi_task_board_backend.dto.UpdateMoveRequest;
import com.yutian.multi_task_board_backend.entity.Task;
import com.yutian.multi_task_board_backend.entity.TaskStatus;
import com.yutian.multi_task_board_backend.exception.TaskNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return taskRepository.findByUserIdOrderByStatusAscIndexAsc(userId);
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

    private int getMaxIndex(int userId, TaskStatus status){
        return taskRepository.findFirstByUserIdAndStatusOrderByIndexDesc(userId,status)
                .map(Task::getIndex)
                .orElse(-1);
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
        theTask.setIndex(getMaxIndex(userId,theTask.getStatus())+1);
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
        theTask.setIndex(tempTask.getIndex());
        return taskRepository.save(theTask);
    }

    @Override
    @Transactional
    public Task updateTaskStatus(int theId, int userId,TaskStatus status) {
        UpdateMoveRequest moveRequest=new UpdateMoveRequest();
        moveRequest.setIndex(getMaxIndex(userId,status)+1);
        moveRequest.setStatus(status);
        return updateTaskStatusAndIndex(theId,userId,moveRequest);

    }


    private void reindexColumn(List<Task> tasks){

        for(int i=0;i< tasks.size();i++){
            tasks.get(i).setIndex(i);
        }
        taskRepository.saveAll(tasks);
    }

    @Override
    @Transactional
    public void deleteTaskById(int theId,int userId) {
        Task task=getTaskById(theId,userId);
        List<Task> tasks=taskRepository.findByUserIdAndStatusOrderByIndexAsc(userId,task.getStatus());
        tasks.removeIf(t->t.getId()==theId);
        taskRepository.delete(task);
        reindexColumn(tasks);

    }

    @Override
    @Transactional
    public Task updateTaskStatusAndIndex(int theId, int userId, UpdateMoveRequest moveRequest) {
        Task task=getTaskById(theId,userId);
        TaskStatus oldStatus=task.getStatus();
        TaskStatus newStatus=(moveRequest.getStatus()==null)? oldStatus:moveRequest.getStatus();
        int newIndex=moveRequest.getIndex();
        List<Task> oldList=taskRepository.findByUserIdAndStatusOrderByIndexAsc(userId,oldStatus);
        oldList.removeIf(t->t.getId()==theId);

        if(newStatus.equals(oldStatus)){
            if(newIndex<0) newIndex=0;
            if(newIndex>oldList.size()) newIndex=oldList.size();
            oldList.add(newIndex,task);
            reindexColumn(oldList);
            return task;
        }else{
            List<Task> targetList=taskRepository.findByUserIdAndStatusOrderByIndexAsc(userId,newStatus);
            if(newIndex<0) newIndex=0;
            if(newIndex>targetList.size()) newIndex=targetList.size();
            task.setStatus(newStatus);
            targetList.add(newIndex,task);
            reindexColumn(oldList);
            reindexColumn(targetList);
            return task;
        }

    }
}
