package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Iterable<User> getAllUser() {
        return repository.findAll();
    }

    public void addUser(User user) {
        repository.save(user);
    }

    public Optional<User> findById(int id) {
        return repository.findById(id);
    }
}