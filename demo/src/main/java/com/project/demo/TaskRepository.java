package com.project.demo;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository <Task, ObjectId> {

    //this is for searching by Id rather than objectId
    //input this info into the service layer because the repo function now includes this below.
    Optional<Task> findTaskByTaskId(String taskId);
}
