package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;

import java.util.Optional;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Override
    <S extends Accident> S save(S s);

    @Override
    Optional<Accident> findById(Integer integer);

    @Override
    Iterable<Accident> findAll();
}