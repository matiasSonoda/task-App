package com.taskApp.taskApp.controller;

import com.taskApp.taskApp.model.AppUser;
import com.taskApp.taskApp.service.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public List<AppUser> getAllUsers(){
        return userService.getAllUsers();
    }
    
    /*@PostMapping
    public void login(@RequestBody AppUser user){
        Optional<AppUser> us = userService.findByUsername(user.getUsername());
        
        
        
        
        
    }*/
    
    @GetMapping("/search")
    public AppUser getUser(@RequestParam Long userId){
        return userService.getUser(userId);
    }
    
    @PostMapping("/search")
    public List<AppUser> searchUsers(@RequestParam(required = false) String mail,
                                  @RequestParam(required = false) String firstname,
                                  @RequestParam(required = false) String lastname,
                                  @RequestParam(required = false) String username )
    {
        return userService.searchUsers(mail, firstname, lastname, username);
    }
    @PostMapping("/save")
    public void saveUser(@RequestBody AppUser user){
        userService.saveUser(user);
    }
    @PutMapping 
    public void updateUser(@RequestBody AppUser user){
        userService.updateUser(user);
    }
    @DeleteMapping
    public void deleteUser(@RequestParam Long id){
        userService.deleteUser(id);
    }
}
