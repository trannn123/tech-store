package com.techstore.service;

import com.techstore.entity.User;
import com.techstore.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository repository;

    @Transactional
    public void register(User user){
        repository.persist(user);
    }

    public User findByUsername(String username){
        return (User) repository.find("username", username).firstResult();
    }
}
