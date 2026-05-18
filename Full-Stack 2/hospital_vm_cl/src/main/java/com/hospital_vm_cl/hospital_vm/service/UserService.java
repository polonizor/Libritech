package com.hospital_vm_cl.hospital_vm.service;

import org.springframework.stereotype.Service;
import com.hospital_vm_cl.hospital_vm.model.User;
import com.hospital_vm_cl.hospital_vm.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UserService {

    private final UserRepository repository;

    // Constructor para inyección
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public String prueba() {
        return "ok";
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User save(User user) {
        return repository.save(user);
    }

    @PostConstruct
    public void init() {
        System.out.println("UserService OK");
    }
}