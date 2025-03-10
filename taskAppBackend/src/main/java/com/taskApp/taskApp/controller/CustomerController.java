
package com.taskApp.taskApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class CustomerController {
    @GetMapping("/index")
    public String index(){
        return "Hello world";
    }
}
