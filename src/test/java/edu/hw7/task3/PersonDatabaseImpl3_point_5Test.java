package edu.hw7.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonDatabaseImpl3_point_5Test {
    @Test
    @DisplayName("dont know how to test just run it")
    public void test() throws InterruptedException {
        PersonDatabaseImpl3point5 personDatabase = new PersonDatabaseImpl3point5();
        Long countOfIter = 10L;
        Thread threadAdder = new Thread(
            () -> {
                for (int i = 0; i < countOfIter; i++) {
                    personDatabase.add(new Person(i, "name" + i, "address" + i, "phone" + i));
                }
            }
        );
        Thread threadFinderByAddress = new Thread(
            () -> {
                for (int i = 0; i < countOfIter; i++) {
                    personDatabase.findByAddress("address" + (int) generateRandom(0, countOfIter - 1));
                }
            }
        );
        Thread threadFinderByName = new Thread(
            () -> {
                for (int i = 0; i < countOfIter; i++) {
                    personDatabase.findByName("name" + (int) generateRandom(0, countOfIter - 1));
                }
            }
        );
        Thread threadFinderByPhone = new Thread(
            () -> {
                for (int i = 0; i < countOfIter; i++) {
                    personDatabase.findByPhone("phone" + (int) generateRandom(0, countOfIter - 1));
                }
            }
        );
        Thread threadRemover = new Thread(
            () -> {
                for (int i = 0; i < countOfIter; i++) {
                    personDatabase.delete((int) generateRandom(0, countOfIter));
                }
            }
        );
        threadAdder.start();
        threadFinderByAddress.start();
        threadRemover.start();
        Thread.sleep(10);
        threadFinderByName.start();
        threadFinderByPhone.start();

        threadAdder.join();
        threadRemover.join();
        threadFinderByAddress.join();
        threadFinderByName.join();
        threadFinderByPhone.join();

    }

    private double generateRandom(double from, double till) {
        return Math.random() * (till - from) + from;
    }

}
