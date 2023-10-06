package com.project.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.model.UserModel;
import com.project.demo.repository.UserRepository;


@Service
public class UserService  {
    //implements UserDetailsService

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> allUsers(){
        return userRepository.findAll(); 
    }

    public Optional<UserModel> findUser(String userId){
        return userRepository.findByUserId(userId);
    }

    public Optional<UserModel> findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    
    public Optional<UserModel> findUserPassword(String password){
        return userRepository.findByPassword(password);
    }
    
    //Save user
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(UserModel user) {
        // Encode the user's password before storing it in the database
        //This will run into error unless .and().csrf().disable(); is added to Config file

        // Save the user to the database
        userRepository.save(user);
    }

    public boolean authenticateUser(String email, String password) {
        Optional <UserModel> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            // Use the PasswordEncoder to verify the entered password
            //.get() allows me to go beyond the Optional statement
            return passwordEncoder.matches(password, user.get().getPassword());
            // return true;
        }
        return false;
    }

    //todo Above 
    //Include the CSRF token in your requests: 
    //If you're using Thymeleaf with Spring Boot, 
    //adding th:action="@{/endpoint}" and th:method="post" to your form 
    //will automatically include the CSRF token.

}
