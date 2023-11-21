package edu.hw7.task3;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.Nullable;

@Log4j2
public class PersonDatabaseImpl3point5 implements PersonDatabase {
    private final HashMap<Integer, Person> database;
    private final HashMap<String, Integer> idByName;
    private final HashMap<String, Integer> idByAddress;
    private final HashMap<String, Integer> idByPhone;
    private final ReadWriteLock readWriteLock;

    public PersonDatabaseImpl3point5() {
        this.database = new HashMap<>();
        readWriteLock = new ReentrantReadWriteLock();
        idByPhone = new HashMap<>();
        idByName = new HashMap<>();
        idByAddress = new HashMap<>();

    }

    @Override public void add(Person person) {
        log.info("create person: " + person.id());
        readWriteLock.writeLock().lock();
        database.put(person.id(), person);
        idByName.put(person.name(), person.id());
        idByAddress.put(person.address(), person.id());
        idByPhone.put(person.phoneNumber(), person.id());
        readWriteLock.writeLock().unlock();

    }

    @Override public synchronized void delete(int id) {
        log.info("delete person: " + id);
        readWriteLock.writeLock().lock();
        Person person = database.remove(id);
        if (person != null) {
            idByName.remove(person.name());
            idByPhone.remove(person.phoneNumber());
            idByAddress.remove(person.address());
        }
        readWriteLock.writeLock().unlock();
    }

    @Override public @Nullable Person findByName(String name) {
        readWriteLock.readLock().lock();
        log.info("finder. name: " + name + " result id by name: " + idByName.get(name));
        if (idByName.get(name) == null) {
            readWriteLock.readLock().unlock();
            return null;
        }
        synchronized (idByName.get(name)) {
            Person person = database.get(idByName.get(name));
            readWriteLock.readLock().unlock();
            return person;
        }
    }

    @Override public @Nullable Person findByAddress(String address) {
        readWriteLock.readLock().lock();
        log.info("finder. address: " + address + " result id by addrs: " + idByAddress.get(address));
        if (idByAddress.get(address) == null) {
            readWriteLock.readLock().unlock();
            return null;
        }
        synchronized (idByAddress.get(address)) {
            Person person = database.get(idByAddress.get(address));
            readWriteLock.readLock().unlock();
            return person;
        }
    }

    @Override public @Nullable Person findByPhone(String phone) {
        readWriteLock.readLock().lock();
        log.info("finder. phone: " + phone + " result id by phone: " + idByPhone.get(phone));
        if (idByPhone.get(phone) == null) {
            readWriteLock.readLock().unlock();
            return null;
        }
        synchronized (idByPhone.get(phone)) {
            Person person = database.get(idByPhone.get(phone));
            readWriteLock.readLock().unlock();
            return person;
        }
    }

}
