package edu.hw7.task3;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.Nullable;

@Log4j2
public class PersonDatabaseImpl implements PersonDatabase {

    private final Map<Integer, Person> database = new HashMap<>();
    private final Object nameLock = new Object();
    private final Object addrLock = new Object();
    private final Object phoneLock = new Object();
    private final Map<String, Integer> idByName = new HashMap<>();
    private final Map<String, Integer> idByAddress = new HashMap<>();
    private final Map<String, Integer> idByPhone = new HashMap<>();

    public PersonDatabaseImpl() {}

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
    public void delete(int id) {
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
            return database.get(idByName.get(name));

        }
    }

    @Override
    public @Nullable Person findByAddress(String address) {
        synchronized (addrLock) {
            log.info("finder. address: " + address + " result id by addr: " + idByAddress.get(address));
            if (idByAddress.get(address) == null) {
                return null;
            }

            return database.get(idByAddress.get(address));

        }
    }

    @Override
    public @Nullable Person findByPhone(String phone) {
        synchronized (phoneLock) {
            log.info("finder. phone: " + phone + " result id by phone: " + idByPhone.get(phone));
            if (idByPhone.get(phone) == null) {
                return null;
            }
            return database.get(idByPhone.get(phone));

        }
    }
}
