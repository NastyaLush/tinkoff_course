package edu.hw7.task3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.Nullable;

@Log4j2
public class PersonDatabaseImpl3point5 implements PersonDatabase {

    private final Map<Integer, Person> database = new HashMap<>();
    private final Map<String, Integer> idByName = new HashMap<>();
    private final Map<String, Integer> idByAddress = new HashMap<>();
    private final Map<String, Integer> idByPhone = new HashMap<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override public void add(Person person) {
        log.info("create person: " + person.id());
        readWriteLock.writeLock().lock();
        try {
            database.put(person.id(), person);
            idByName.put(person.name(), person.id());
            idByAddress.put(person.address(), person.id());
            idByPhone.put(person.phoneNumber(), person.id());
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    @Override public void delete(int id) {
        readWriteLock.writeLock().lock();
        try {
            log.info("delete person: " + id);
            Person person = database.remove(id);
            if (person != null) {
                idByName.remove(person.name());
                idByPhone.remove(person.phoneNumber());
                idByAddress.remove(person.address());
            }
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override public @Nullable Person findByName(String name) {
        readWriteLock.readLock().lock();
        try {
            log.info("finder. name: " + name + " result id by name: " + idByName.get(name));
            if (idByName.get(name) == null) {
                return null;
            }
            return database.get(idByName.get(name));

        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override public @Nullable Person findByAddress(String address) {
        readWriteLock.readLock().lock();
        try {
            log.info("finder. address: " + address + " result id by addrs: " + idByAddress.get(address));
            if (idByAddress.get(address) == null) {
                return null;
            }

            return database.get(idByAddress.get(address));

        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override public @Nullable Person findByPhone(String phone) {
        readWriteLock.readLock().lock();
        try {
            log.info("finder. phone: " + phone + " result id by phone: " + idByPhone.get(phone));
            if (idByPhone.get(phone) == null) {
                return null;
            }

            return database.get(idByPhone.get(phone));

        } finally {
            readWriteLock.readLock().unlock();
        }
    }

}
