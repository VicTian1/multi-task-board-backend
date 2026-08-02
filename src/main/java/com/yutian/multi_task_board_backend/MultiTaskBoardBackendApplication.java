package com.yutian.multi_task_board_backend;

import com.yutian.multi_task_board_backend.entity.Task;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.time.LocalDate;
import java.util.List;


@SpringBootApplication
public class MultiTaskBoardBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiTaskBoardBackendApplication.class, args);
	}

    /*@Bean
    public CommandLineRunner commandLineRunner(TaskDAO taskDAO){
        return runner->{
            //createTask(taskDAO);
            // findTask(taskDAO);
            // findAllTask(taskDAO);
            //updateTask(taskDAO);
            //deleteTask(taskDAO);
        };
    }

    private void deleteTask(TaskDAO taskDAO) {
        taskDAO.deleteById(3);
    }

    private void updateTask(TaskDAO taskDAO) {
        Task t=taskDAO.findById(3);
        t.setDescription("watch some films");
        taskDAO.update(t);

    }

    private void findAllTask(TaskDAO taskDAO) {

        List<Task> tasks= taskDAO.findAll();
        for(Task t:tasks){
            System.out.println(t);
        }
    }

    private void findTask(TaskDAO taskDAO) {
        int id= 1;
        Task t=taskDAO.findById(id);
        System.out.println("find a task: "+t);
    }


    private void createTask(TaskDAO taskDAO){
        Task t= new Task("Rest","have a rest", "general", LocalDate.of(2026,8,31),"to do");
        taskDAO.save(t);
        System.out.println("save a new task"+ t);
    }
    */






}


