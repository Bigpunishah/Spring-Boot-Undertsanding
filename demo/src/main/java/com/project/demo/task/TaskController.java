package com.project.demo.task;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    
    @GetMapping("/{taskId}") //Use the DB taskId which can be updated in repo & service layer to copy & paste into the localhost8080/api/v1/tasks/....
                        // If you dont want the objectId exposed to public you can implement the code in the Repository class
    public ResponseEntity<Optional<Task>> getSingleTask(@PathVariable String taskId){ //This is using optional to avoid null pointer
        return new ResponseEntity<Optional<Task>>(taskService.singleTask(taskId), HttpStatus.OK);
    }
}
