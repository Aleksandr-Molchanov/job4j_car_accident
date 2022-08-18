package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.Optional;

@Service
public class AccidentService {

    private AccidentRepository accidentRepository;

    public AccidentService(AccidentRepository accidentRepository) {
        this.accidentRepository = accidentRepository;
    }

    public Iterable<Accident> getAllAccidents() {
        return accidentRepository.findAllAccidents();
    }

    public void addAccident(Accident accident) {
        accidentRepository.save(accident);
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidentRepository.findById(id));
    }

    public void update(Accident accident) {
        accidentRepository.save(accident);
    }
}
