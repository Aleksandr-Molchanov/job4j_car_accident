package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.Accident;
import ru.job4j.repository.AccidentMem;

import java.util.Collection;

@Service
public class AccidentService {

    private AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public Collection<Accident> getAllAccidents() {
        return accidentMem.findAll();
    }

    public void addAccident(Accident accident) {
        accidentMem.add(accident);
    }

    public Accident findById(int id) {
        return accidentMem.findById(id);
    }

    public void update(Accident accident) {
        accidentMem.update(accident);
    }
}
