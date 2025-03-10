package com.taskApp.taskApp.service;

import com.taskApp.taskApp.model.Task;
import com.taskApp.taskApp.model.AppUser;
import com.taskApp.taskApp.repository.TaskRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    
    public List<Task> findAllByUser(AppUser user){
        return taskRepository.findByUser(user);
    }
    
    public Optional<Task> getTask(Long id){
        return taskRepository.findById(id);
    }
    
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
    public List<Task> getFilterTasksByUserAndTitle(AppUser user, String name){
        return taskRepository.findByUserAndTitleContaining(user, name);
    }
    public Task saveTask(Task task){
        return taskRepository.save(task);
    }
    public Task updateTask(Task task){
        if(!taskRepository.existsById(task.getIdTask())){
            return null;
        }
        return saveTask(task);
    }
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
    public void deleteAllTasks(){
        taskRepository.deleteAll();
    }
}
