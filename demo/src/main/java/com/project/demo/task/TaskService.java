package com.project.demo.task;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // The service layer holds all the behind the scene functions.
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> allTasks(){
        return taskRepository.findAll();
    }

    //Optional means it may or may not contain a non-null value. Avoids null pointer ref
    //creating uopdated version
    // public Optional<Task> singleTask(ObjectId id){
    //     return taskRepository.findById(id);
    // }

    public Optional<Task> singleTask(String taskId){
        return taskRepository.findTaskByTaskId(taskId);
    }

}
