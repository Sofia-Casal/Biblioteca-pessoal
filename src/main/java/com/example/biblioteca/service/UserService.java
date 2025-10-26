package com.example.biblioteca.service;

import com.example.biblioteca.model.User;
import com.example.biblioteca.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public Optional<User> autenticar(String email, String senha) {
        return repo.findByEmail(email)
                .filter(u -> u.getPassword().equals(senha));
    }
}
