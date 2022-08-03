package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.AccidentType;
import ru.job4j.repository.AccidentTypeMem;

import java.util.Collection;

@Service
public class AccidentTypeService {

    private AccidentTypeMem accidentTypeMem;

    public AccidentTypeService(AccidentTypeMem accidentTypeMem) {
        this.accidentTypeMem = accidentTypeMem;
    }

    public Collection<AccidentType> getAllAccidentsType() {
        return accidentTypeMem.findAll();
    }

    public void addAccidentType(AccidentType accidentType) {
        accidentTypeMem.add(accidentType);
    }

    public AccidentType findById(int id) {
        return accidentTypeMem.findById(id);
    }

    public void update(AccidentType accidentType) {
        accidentTypeMem.update(accidentType);
    }
}