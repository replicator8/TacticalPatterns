package org.example.DomainModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryOrderRepository {
    private final Map<Integer, TransportationOrder> orders = new HashMap<>();

    public void save(TransportationOrder to) {
        orders.put(to.getId(), to);
        System.out.println("Заявка успешно сохранена!");
    }

    public void removeById(int id) {
        orders.remove(id);
        System.out.println("Заявка успешно удалена!");
    }

    public TransportationOrder findById(int id) {
        return orders.get(id);
    }

    public List<TransportationOrder> findAll() {
        return new ArrayList<>(orders.values());
    }
}
