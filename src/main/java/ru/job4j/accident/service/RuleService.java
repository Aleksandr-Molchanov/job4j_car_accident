package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleHibernate;

import java.util.Collection;

@Service
public class RuleService {

    private RuleHibernate ruleMem;

    public RuleService(RuleHibernate ruleMem) {
        this.ruleMem = ruleMem;
    }

    public Collection<Rule> getAllRule() {
        return ruleMem.findAll();
    }

    public Rule findById(int id) {
        return ruleMem.findById(id);
    }
}