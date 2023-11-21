package edu.hw7.task3;

import java.util.HashMap;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.Nullable;

@Log4j2
public class PersonDatabaseImpl implements PersonDatabase {
    private final HashMap<Integer, Person> database;
    private final Object nameLock = new Object();
    private final Object addrLock = new Object();
    private final Object phoneLock = new Object();
    private final HashMap<String, Integer> idByName;
    private final HashMap<String, Integer> idByAddress;
    private final HashMap<String, Integer> idByPhone;

    public PersonDatabaseImpl() {
        this.database = new HashMap<>();
        idByPhone = new HashMap<>();
        idByName = new HashMap<>();
        idByAddress = new HashMap<>();
    }

    @Override
    public void add(Person person) {
        log.info("create person: " + person.id());
        synchronized (nameLock) {
            synchronized (addrLock) {
                synchronized (phoneLock) {
                    database.put(person.id(), person);
                    idByName.put(person.name(), person.id());
                    idByAddress.put(person.address(), person.id());
                    idByPhone.put(person.phoneNumber(), person.id());
                }
            }
        }

    }

    @Override
    public synchronized void delete(int id) {
        log.info("delete person: " + id);
        synchronized (nameLock) {
            synchronized (addrLock) {
                synchronized (phoneLock) {
                    Person person = database.remove(id);
                    if (person != null) {
                        idByName.remove(person.name());
                        idByPhone.remove(person.phoneNumber());
                        idByAddress.remove(person.address());
                    }
                }
            }
        }
    }

    @Override
    public @Nullable Person findByName(String name) {
        synchronized (nameLock) {
            log.info("finder. name: " + name + " result id by name: " + idByName.get(name));
            if (idByName.get(name) == null) {
                return null;
            }
            synchronized (idByName.get(name)) {
                return database.get(idByName.get(name));
            }
        }
    }

    @Override
    public @Nullable Person findByAddress(String address) {
        synchronized (addrLock) {
            log.info("finder. address: " + address + " result id by addr: " + idByAddress.get(address));
            if (idByAddress.get(address) == null) {
                return null;
            }
            synchronized (idByAddress.get(address)) {
                return database.get(idByAddress.get(address));
            }
        }
    }

    @Override
    public @Nullable Person findByPhone(String phone) {
        synchronized (phoneLock) {
            log.info("finder. phone: " + phone + " result id by phone: " + idByPhone.get(phone));
            if (idByPhone.get(phone) == null) {
                return null;
            }
            synchronized (idByPhone.get(phone)) {
                return database.get(idByPhone.get(phone));
            }
        }
    }
}
