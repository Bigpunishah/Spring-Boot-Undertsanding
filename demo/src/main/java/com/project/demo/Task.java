package com.project.demo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//! All names in reference to the @Document must be the same name as the DB names
//TODO Ensure correct spelling

@Document(collection = "tasks") //Shows that this is a infromation coming from db
@Data //Makes it to where dont need to write out getter & setter
@AllArgsConstructor //takes all the values as constructor
@NoArgsConstructor //takes no values for constructor
public class Task {

    @Id  //specifices that this is an id marker
    private ObjectId id;

    private List<String> tasks;

    private List<String> priority;

    
}
