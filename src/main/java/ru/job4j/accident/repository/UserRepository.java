package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Override
    <S extends User> S save(S s);

    @Override
    Optional<User> findById(Integer integer);

    @Override
    Iterable<User> findAll();
}