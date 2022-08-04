package ru.job4j.repository;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RuleMem {

    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();

    private final AtomicInteger size = new AtomicInteger(3);

    public RuleMem() {
        rules.put(1, Rule.of(1, "Статья. 1"));
        rules.put(2, Rule.of(2, "Статья. 2"));
        rules.put(3, Rule.of(3, "Статья. 3"));
    }

    public Collection<Rule> findAll() {
        return rules.values();
    }

    public void add(Rule rule) {
        int id = size.incrementAndGet();
        rule.setId(id);
        rules.put(id, rule);
    }

    public Rule findById(int id) {
        return rules.get(id);
    }

    public void update(Rule rule) {
        rules.put(rule.getId(), rule);
    }
}