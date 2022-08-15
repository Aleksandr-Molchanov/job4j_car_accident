package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeHibernate;

import java.util.Collection;

@Service
public class AccidentTypeService {

    private AccidentTypeHibernate accidentTypeMem;

    public AccidentTypeService(AccidentTypeHibernate accidentTypeMem) {
        this.accidentTypeMem = accidentTypeMem;
    }

    public Collection<AccidentType> getAllAccidentsType() {
        return accidentTypeMem.findAll();
    }

    public AccidentType findById(int id) {
        return accidentTypeMem.findById(id);
    }
}