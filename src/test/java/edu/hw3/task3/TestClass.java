package edu.hw3.task3;

import java.util.Objects;

public class TestClass {

    private final String name;
    private final Integer age;

    public TestClass(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TestClass testClass = (TestClass) o;
        return Objects.equals(name, testClass.name) && Objects.equals(age, testClass.age);
    }
}
