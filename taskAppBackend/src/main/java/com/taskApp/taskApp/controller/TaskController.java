package com.taskApp.taskApp.controller;

import com.taskApp.taskApp.model.Task;
import com.taskApp.taskApp.model.AppUser;
import com.taskApp.taskApp.service.SimpleOrderManager;
import com.taskApp.taskApp.service.TaskService;
import com.taskApp.taskApp.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private SimpleOrderManager simpleOrderManager;
    @Autowired
    private UserService userService;
    
    /*@GetMapping
    public ResponseEntity<List<Task>> getAllTasks(Principal principal){
        Optional<AppUser> userOp = userService.findByUsername(principal.getName());
        if (userOp.isPresent()){
            AppUser user = userOp.get();
            if(!user.getUsername().equals(principal.getName())){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            List<Task> tasks = taskService.findAllByUser(user);
            return ResponseEntity.ok(tasks);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }*/
    
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id){
        System.out.println(id);
        Task task = taskService.getTask(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
        return ResponseEntity.status(HttpStatus.OK).body(task);

    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks(){
            List<Task> tasks = taskService.getAllTasks();
            return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Task>> getFilterTasks(Principal principal, @RequestParam @NotBlank String name){
        AppUser user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
        if ( !user.getUsername().equals(principal.getName())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<Task> tasks = taskService.getFilterTasksByUserAndTitle(user, name);
        return ResponseEntity.ok(tasks);
    }
    
    /*@PostMapping
    public ResponseEntity<Task> saveTasks(Principal principal,@Valid @RequestBody Task task){
        AppUser user = userService.findByUsername(principal.getName()) 
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
        if(!user.getUsername().equals(principal.getName())){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        task.setUser(user);
        Task createdTask = taskService.saveTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        }*/
    @PostMapping
    public ResponseEntity<Task> saveTasks(@RequestBody @Valid Task task){
        Task createdTask = taskService.saveTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        }
    
    /*@PutMapping
    public ResponseEntity<Task> updateTask(Principal principal,@Valid @RequestBody Task task){
        AppUser user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
        if(!user.getUsername().equals(principal.getName())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        
        Task updateTask = taskService.updateTask(task);
        return ResponseEntity.status(HttpStatus.OK).body(updateTask);
    }*/
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task){  
        System.out.println(id);
        System.out.println(task);
        if(task.getIdTask() == null || !id.equals(task.getIdTask())){
            return ResponseEntity.badRequest().build();
        }
        Task updateTask = taskService.updateTask(task);
        if(updateTask == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(updateTask);
    }
    
    /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(Principal principal, @PathVariable @NotNull Long id){
        AppUser user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found."));
        if ( !user.getUsername().equals(principal.getName())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tiene autorización.");
        }
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).body("Tarea eliminada con exito.");
    }*/
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable @NotNull Long id){
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).body("Tarea eliminada con exito.");
    }
    /*
    @DeleteMapping("/delete/all")
    public ResponseEntity<String> DeleteAllTasks(Principal principal){
        AppUser user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found."));
        if(!user.getUsername().equals(principal.getName())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tiene autorización.");
        }
        taskService.deleteAllTasks();
        return ResponseEntity.status(HttpStatus.OK).body("Se eliminaron todas las tareas con exito.");
    }*/
}
