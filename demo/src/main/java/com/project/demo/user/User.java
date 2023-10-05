package com.project.demo.user;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User  {
    // implements UserDetails
    @Id
    private String userId;

    private String name;

    private String email;

    private String password;

    private List<String> role;
   
}
