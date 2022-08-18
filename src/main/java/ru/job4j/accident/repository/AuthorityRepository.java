package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Authority;

import java.util.Optional;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {

    @Override
    <S extends Authority> S save(S s);

    @Override
    Optional<Authority> findById(Integer integer);

    @Override
    Iterable<Authority> findAll();

    Authority findByAuthority(String authority);
}