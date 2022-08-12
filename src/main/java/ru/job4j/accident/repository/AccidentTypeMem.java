package ru.job4j.accident.repository;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentTypeMem {

    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>();

    private final AtomicInteger size = new AtomicInteger(3);

    public AccidentTypeMem() {
        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));
    }

    public Collection<AccidentType> findAll() {
        return types.values();
    }

    public void add(AccidentType accidentType) {
        int id = size.incrementAndGet();
        accidentType.setId(id);
        types.put(id, accidentType);
    }

    public AccidentType findById(int id) {
        return types.get(id);
    }

    public void update(AccidentType accidentType) {
        types.put(accidentType.getId(), accidentType);
    }
}