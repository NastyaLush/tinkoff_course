package edu.hw10.task1;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RandomObjectGeneratorAnnotationsTest {

    @Test
    public void nextObject_shouldGenerateClass()
        throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException,
        NoSuchFieldException, NoSuchMethodException {
        Map<Class<?>, Supplier<?>> declaredObjects = supplierGenerator();
        TestClass expectedObject = new TestClass("string", 5, 3, -1, 2);
        RandomObjectGeneratorAnnotations randomObjectGenerator = new RandomObjectGeneratorAnnotations(
            declaredObjects, getComparableClasses());

        TestClass
            actualObject = randomObjectGenerator.nextObject(TestClass.class);

        assertEquals(expectedObject, actualObject);
    }

    private Map<Class<?>, Supplier<?>> supplierGenerator() {
        Map<Class<?>, Supplier<?>> declaredObjects = new HashMap<>();
        declaredObjects.put(String.class, () -> "string");
        declaredObjects.put(Integer.class, () -> 1);
        return declaredObjects;
    }

    private List<Class<?>> getComparableClasses() {
        List<Class<?>> declaredObjects = new ArrayList<>();
        declaredObjects.add(Integer.class);
        declaredObjects.add(int.class);
        return declaredObjects;
    }

    @ParameterizedTest
    @ValueSource(strings = {"wrongMinMaxGenerator", "wrongTypeGenerator"})
    public void nextObject_shouldThrowExceptionIfSomethingWrong(String nameOfGeneratorMethod) {
        RandomObjectGeneratorAnnotations randomObjectGenerator = new RandomObjectGeneratorAnnotations(
            supplierGenerator(), getComparableClasses());

        assertThrows(
            IllegalArgumentException.class,
            () -> randomObjectGenerator.nextObject(TestClass.class, nameOfGeneratorMethod)
        );

    }

    @Data
    static class TestClass {

        private final String string;
        private final Integer integer;
        private final Integer integer1;
        private final Integer integer2;
        private final Integer integer3;

        public TestClass(
            @NotNull String string,
            @NotNull @Min(5) Integer integer,
            @Max(3) Integer integer1,
            @Min(-1) @Max(7) Integer integer2,
            @Min(2) @Max(2) Integer integer3
        ) {
            this.string = string;
            this.integer = integer;
            this.integer1 = integer1;
            this.integer2 = integer2;
            this.integer3 = integer3;
        }

        public static TestClass wrongTypeGenerator(
            @NotNull @Min(2) String string,
            @NotNull @Min(5) Integer integer,
            @Max(3) Integer integer1,
            @Min(-1) @Max(7) Integer integer2,
            @Min(2) @Max(2) Integer integer3
        ) {
            return new TestClass(string, integer, integer1, integer2, integer3);

        }

        public static TestClass wrongMinMaxGenerator(
            @NotNull String string,
            @NotNull @Min(5) Integer integer,
            @Max(3) Integer integer1,
            @Min(3) @Max(1) Integer integer2,
            @Min(2) @Max(2) Integer integer3
        ) {
            return new TestClass(string, integer, integer1, integer2, integer3);

        }
    }
}
