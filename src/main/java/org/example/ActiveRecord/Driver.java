package org.example.ActiveRecord;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Driver {
    private final int id;
    private final String fio;
    private boolean isActive;
    private final List<Shift> schedule = new ArrayList<>();

    public Driver(int id, String fio, boolean isActive) {
        this.id = id;
        this.fio = fio;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void addShift(Shift shift) {
        for (Shift s : schedule) {
            if (s.overlapsWith(shift)) {
                throw new IllegalArgumentException("Смена пересекается с существующей сменой!");
            }
        }
        schedule.add(shift);
    }

    public List<Shift> getShiftsForDate(LocalDateTime date) {
        List<Shift> shiftForDate = new ArrayList<>();
        for (Shift s : schedule) {
            if (s.isOnDate(date)) {
                shiftForDate.add(s);
            }
        }
        return shiftForDate;
    }

    public boolean isAvailableAt(LocalDateTime dateTime) {
        for (Shift s : schedule) {
            if (s.isDuring(dateTime)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
