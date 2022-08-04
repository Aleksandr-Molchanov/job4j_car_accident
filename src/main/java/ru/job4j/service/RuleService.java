package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.Rule;
import ru.job4j.repository.RuleMem;

import java.util.Collection;

@Service
public class RuleService {

    private RuleMem ruleMem;

    public RuleService(RuleMem ruleMem) {
        this.ruleMem = ruleMem;
    }

    public Collection<Rule> getAllRule() {
        return ruleMem.findAll();
    }

    public void addRule(Rule rule) {
        ruleMem.add(rule);
    }

    public Rule findById(int id) {
        return ruleMem.findById(id);
    }

    public void update(Rule rule) {
        ruleMem.update(rule);
    }
}