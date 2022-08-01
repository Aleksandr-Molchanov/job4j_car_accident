package ru.job4j.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        accidents.put(1, new Accident(1, "Пересечение сплошной", "Автомобиль проехал через сплошную линию", "Москва, ул.Вавилова 1"));
        accidents.put(2, new Accident(2, "Парковка в неположенном месте", "Автомобиль припарковался в неположенном месте", "Москва, ул.Валовая 1"));
        accidents.put(3, new Accident(3, "ДТП с участием пешехода", "Автомобиль сбил пешехода на переходе", "Москва, ул.Окская 1"));
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }
}
