package com.project.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.demo.model.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String>{
    
    //!When using repo in MUST include findBy
    Optional<UserModel> findByUserId(String userId);

    Optional<UserModel> findByEmail(String email);

    Optional<UserModel> findByPassword(String password);
    
}