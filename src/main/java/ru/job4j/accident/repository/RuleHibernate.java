package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.List;

@Repository
public class RuleHibernate implements DBStore {

    private final SessionFactory sf;

    public RuleHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Rule findById(int id) {
        return tx(
                session -> session.get(Rule.class, id), sf
        );
    }

    public List<Rule> findAll() {
        return tx(
                session -> session.createQuery(
                        "from Rule"
                ).list(), sf
        );
    }
}