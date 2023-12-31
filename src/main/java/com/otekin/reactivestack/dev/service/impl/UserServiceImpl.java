package com.otekin.reactivestack.dev.service.impl;

import com.otekin.reactivestack.dev.domain.User;
import com.otekin.reactivestack.dev.repository.UserRepository;
import com.otekin.reactivestack.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<User> save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public Mono<User> delete(String id) {
        return this.userRepository.findById(id).flatMap(p -> this.userRepository.deleteById(p.getId()).thenReturn(p));

    }

    @Override
    public Mono<User> update(String id, User user) {
        return this.userRepository.findById(id).flatMap(user1 -> {
            user.setId(id);
            return save(user);
        }).switchIfEmpty(Mono.empty());
    }

    @Override
    public Flux<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Mono<User> findById(String id) {
        return this.userRepository.findById(id);
    }
}
