package com.project.demo.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.demo.model.UserModel;
import com.project.demo.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    //Find user by email
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //Using optional to check if user.isPresent()
        Optional<UserModel> user = userRepository.findByEmail(email);
        //If emtpy then..
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }

        //Collecting the roles from user searched by email
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        for(String role : user.get().getRoles()){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }

        //Return a new User of type UserDetails
        return new User(user.get().getEmail(), user.get().getPassword(), authorities);
        

    }
    
}
    

