package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import javax.persistence.Query;
import java.util.List;

@Repository
public class AccidentHibernate implements DBStore {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident add(Accident accident) {
        return tx(
                session -> {
                    session.save(accident);
                    return accident;
                }, sf
        );
    }

    public void update(Accident accident) {
        tx(
                session -> {
                    session.merge(accident);
                    return new Object();
                }, sf
        );
    }

    public boolean delete(int id) {
        return tx(
                session -> {

                    final Query query = session.createQuery(
                            "delete Accident a "
                                    + " where a.id = :id");
                    query.setParameter("id", id);
                    int rsl = query.executeUpdate();
                    return rsl != 0;
                }, sf
        );
    }

    public Accident findById(int id) {
        return tx(
                session -> session.get(Accident.class, id), sf
        );
    }

    public List<Accident> findAll() {
        return tx(
                session -> session.createQuery(
                        "select distinct a from Accident a "
                                + "join fetch a.accidentType at "
                                + "join fetch a.rules r ", Accident.class
                ).list(), sf
        );
    }
}