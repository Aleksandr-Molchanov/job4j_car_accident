package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Authority;
import ru.job4j.accident.repository.AuthorityRepository;

import java.util.Optional;

@Service
public class AuthorityService {

    private AuthorityRepository repository;

    public AuthorityService(AuthorityRepository repository) {
        this.repository = repository;
    }

    public Iterable<Authority> getAllAccidents() {
        return repository.findAll();
    }

    public void addAuthority(Authority authority) {
        repository.save(authority);
    }

    public Optional<Authority> findById(int id) {
        return repository.findById(id);
    }

    public Authority findByAuthority(String authority) {
        return repository.findByAuthority(authority);
    }
}