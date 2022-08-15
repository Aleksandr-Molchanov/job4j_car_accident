package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;

@Repository
public class AccidentTypeHibernate implements DBStore {

    private final SessionFactory sf;

    public AccidentTypeHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public AccidentType findById(int id) {
        return tx(
                session -> session.get(AccidentType.class, id), sf
        );
    }

    public List<AccidentType> findAll() {
        return tx(
                session -> session.createQuery(
                        "from AccidentType"
                ).list(), sf
        );
    }
}