package ru.job4j.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.model.Accident;
import ru.job4j.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    private final AtomicInteger size = new AtomicInteger(3);

    public AccidentMem() {
        accidents.put(1, new Accident(1, "ДТП 2-х автомобилей", "Автомобиль врезался в другой автомобиль", "Москва, ул.Вавилова 1", AccidentType.of(1, "Две машины")));
        accidents.put(2, new Accident(2, "ДТП с участием пешехода", "Автомобиль сбил пешехода", "Москва, ул.Валовая 1", AccidentType.of(2, "Машина и человек")));
        accidents.put(3, new Accident(3, "ДТП с участием велосипедиста", "Автомобиль сбил велосипедиста", "Москва, ул.Окская 1", AccidentType.of(3, "Машина и велосипед")));
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public void add(Accident accident) {
        int id = size.incrementAndGet();
        accident.setId(id);
        accidents.put(id, accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public void update(Accident accident) {
        accidents.put(accident.getId(), accident);
    }
}
