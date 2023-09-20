package com.project.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    
    @Autowired
    private TaskService taskService;
    
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){ //ResponseEntity allows for the HTTPS status code to be included as well as some others.

        return new ResponseEntity<List<Task>>(taskService.allTasks() , HttpStatus.OK); //HttpSattus.OK sends 200 code not in HTML file. Can be seen in Curl
    }
}
