package edu.hw7.task3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.jetbrains.annotations.Nullable;

public class PersonDatabaseImpl implements PersonDatabase {
    private final ConcurrentHashMap<Integer, Person> database;

    public PersonDatabaseImpl() {
        this.database = new ConcurrentHashMap();
    }

    @Override
    public void add(Person person) {
        database.put(person.id(), person);
    }

    @Override
    public void delete(int id) {
        database.remove(id);
    }

    @Override
    public synchronized @Nullable Person findByName(String name) {
        for (Map.Entry<Integer, Person> person : database.entrySet()) {
            if (person.getValue().name().equals(name)) {
                return person.getValue();
            }
        }
        return null;
    }

    @Override
    public synchronized @Nullable Person findByAddress(String address) {
        for (Map.Entry<Integer, Person> person : database.entrySet()) {
            if (person.getValue().address().equals(address)) {
                return person.getValue();
            }
        }
        return null;
    }

    @Override
    public synchronized @Nullable Person findByPhone(String phone) {
        for (Map.Entry<Integer, Person> person : database.entrySet()) {
            if (person.getValue().address().equals(phone)) {
                return person.getValue();
            }
        }
        return null;
    }
}
