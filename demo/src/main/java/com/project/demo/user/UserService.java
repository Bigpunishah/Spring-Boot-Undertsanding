package com.project.demo.user;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService  {
    //implements UserDetailsService

    @Autowired
    private UserRepository userRepository;

    public List<User> allUsers(){
        return userRepository.findAll(); 
    }

    public Optional<User> findUser(String userId){
        return userRepository.findByUserId(userId);
    }

    public Optional<User> findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    
    public Optional<User> findUserPassword(String password){
        return userRepository.findByPassword(password);
    }
    
    //Save user
     @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        // Encode the user's password before storing it in the database
        //This will run into error unless .and().csrf().disable(); is added to Config file

        // Save the user to the database
        userRepository.save(user);
    }

    public boolean authenticateUser(String email, String password) {
        Optional <User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            // Use the PasswordEncoder to verify the entered password
            //.get() allows me to go beyond the Optional statement
            return passwordEncoder.matches(password, user.get().getPassword());
        }
        return false;
    }

}
