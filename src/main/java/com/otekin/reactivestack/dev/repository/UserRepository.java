package com.otekin.reactivestack.dev.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.otekin.reactivestack.dev.domain.User;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    
}
