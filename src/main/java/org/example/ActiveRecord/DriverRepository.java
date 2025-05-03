package org.example.ActiveRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverRepository {
    private final Map<Integer, Driver> drivers = new HashMap<>();

    public void save(Driver dr) {
        drivers.put(dr.getId(), dr);
        System.out.println("Водитель сохранен: " + dr);
    }

    public Driver findById(int id) {
        return drivers.get(id);
    }

    public List<Driver> findAll() {
        return new ArrayList<>(drivers.values());
    }
}
