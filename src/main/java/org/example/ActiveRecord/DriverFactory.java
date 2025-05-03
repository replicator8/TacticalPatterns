package org.example.ActiveRecord;

public class DriverFactory {
    private static int idCounter = 1;

    public static Driver createDriver(String fio, boolean isActive) {
        return new Driver(idCounter++, fio, isActive);
    }
}