package edu.hw6.task1;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

public class DiskMapTest {

    public static Stream<Arguments> containsKeyProvider() {
        DiskMap diskMap = null;
        try {
            diskMap = new DiskMap("test");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        diskMap.clear();
        diskMap.put("1", "2");
        diskMap.put("2", "2");
        diskMap.put("3", "2");
        diskMap.put("4", "2");
        return Stream.of(
                Arguments.of(diskMap, "1", true),
                Arguments.of(diskMap, "3", true),
                Arguments.of(diskMap, "5", false)

        );
    }

    public static Stream<Arguments> containsValueProvider() {
        DiskMap diskMap = null;
        try {
            diskMap = new DiskMap("test");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        diskMap.clear();
        diskMap.put("1", "2");
        diskMap.put("2", "3");
        diskMap.put("3", "2");
        diskMap.put("4", "2");
        return Stream.of(
                Arguments.of(diskMap, "2", true),
                Arguments.of(diskMap, "3", true),
                Arguments.of(diskMap, "5", false)

        );
    }

    public static Stream<Arguments> getProvider() {
        DiskMap diskMap = null;
        try {
            diskMap = new DiskMap("test");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        diskMap.clear();
        diskMap.put("1", "2");
        diskMap.put("2", "3");
        diskMap.put("3", "2");
        diskMap.put("4", "2");
        return Stream.of(
                Arguments.of(diskMap, "1", "2"),
                Arguments.of(diskMap, "2", "3"),
                Arguments.of(diskMap, "3", "2"),
                Arguments.of(diskMap, "4", "2"),
                Arguments.of(diskMap, "5", null)

        );
    }

    @AfterAll
    private static void deleteFiles() throws IOException {

        if (Files.exists(Path.of("test"))) {
            Files.delete(Path.of("test"));
        }

    }

    @Test
    @DisplayName("is empty return true if map is empty")
    public void isEmpty_shouldReturnTrueIfMapIsEmpty() {
        try {
            DiskMap diskMap = new DiskMap("test");
            boolean expectedAnswer = true;

            boolean actualAnswer = diskMap.isEmpty();

            assertEquals(expectedAnswer, actualAnswer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("is empty return false if map is not empty")
    public void isEmpty_shouldReturnFalseIfMapIsNotEmpty() {
        try {
            DiskMap diskMap = new DiskMap("test");
            boolean expectedAnswer = false;

            boolean actualAnswer = diskMap.isEmpty();

            assertEquals(expectedAnswer, actualAnswer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("is empty return false if map is not empty")
    public void clear_shouldClearFile() {
        try {
            DiskMap diskMap = new DiskMap("test");
            diskMap.put("1", "2");
            diskMap.clear();
            boolean expectedAnswer = true;

            boolean actualAnswer = diskMap.isEmpty();

            assertEquals(expectedAnswer, actualAnswer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("size increase if add objects")
    public void size_shouldIncreaseIfAddObject() {
        try {
            DiskMap diskMap = new DiskMap("test");
            diskMap.clear();
            diskMap.put("1", "2");
            int expectedSize = 1;

            int actualSize = diskMap.size();

            assertEquals(expectedSize, actualSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("size decrease if add objects")
    public void size_shouldDecreaseIfRemoveObject() {
        try {
            DiskMap diskMap = new DiskMap("test");
            diskMap.clear();
            diskMap.put("1", "2");
            diskMap.put("2", "2");
            diskMap.remove("1");
            int expectedSize = 1;

            int actualSize = diskMap.size();

            assertEquals(expectedSize, actualSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest(name = "Index {0} key {1} contains key should return {2}")
    @MethodSource("containsKeyProvider")
    public void containsKey_shouldWorkCorrectly(DiskMap diskMap, String key, boolean expectedAnswer) {
        boolean actualAnswer = diskMap.containsKey(key);

        assertEquals(expectedAnswer, actualAnswer);
    }

    @ParameterizedTest(name = "Index {0} value {1} expected answer {2}")
    @MethodSource("containsValueProvider")
    public void containsValue_shouldWorkCorrectly(DiskMap diskMap, String value, boolean expectedAnswer) {
        boolean actualAnswer = diskMap.containsValue(value);

        assertEquals(expectedAnswer, actualAnswer);
    }

    @ParameterizedTest(name = "Index {0} key {1} expected answer {2}")
    @MethodSource("getProvider")
    public void get_shouldWorkCorrectly(DiskMap diskMap, String key, String expectedAnswer) {
        String actualAnswer = diskMap.get(key);

        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test()
    @DisplayName("put should work correctly")
    public void put_shouldWorkCorrectly() {
        DiskMap diskMap = null;
        try {
            diskMap = new DiskMap("test");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        diskMap.clear();
        diskMap.put("1", "2");
        boolean expectedAnswer = true;

        boolean actualAnswer = diskMap.containsKey("1");

        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test()
    @DisplayName("put should work correctly")
    public void put_shouldWorkCorrectlyWithTheSameKey() {
        DiskMap diskMap = null;
        try {
            diskMap = new DiskMap("test");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        diskMap.clear();
        diskMap.put("1", "2");
        diskMap.put("1", "3");

        boolean actualAnswer = diskMap.containsValue("3");

        assertTrue(actualAnswer);
        assertFalse(diskMap.containsValue("2"));
    }

    @Test()
    @DisplayName("remove should work correctly")
    public void remove_shouldWorkCorrectly() {
        DiskMap diskMap = null;
        try {
            diskMap = new DiskMap("test");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        diskMap.clear();
        diskMap.put("1", "2");
        diskMap.remove("1");
        boolean expectedAnswer = false;

        boolean actualAnswer = diskMap.containsKey("1");

        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test()
    @DisplayName("put all should work correctly")
    public void putAll_shouldWorkCorrectly() {
        DiskMap diskMap = null;
        try {
            diskMap = new DiskMap("test");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        diskMap.clear();
        Map<String, String> map = new HashMap<>();
        map.put("1", "2");
        map.put("2", "2");
        map.put("3", "2");
        diskMap.putAll(map);


        assertTrue(diskMap.containsKey("1"));
        assertTrue(diskMap.containsKey("2"));
        assertTrue(diskMap.containsKey("3"));
        assertFalse(diskMap.containsKey("4"));
    }

    @Test()
    @DisplayName("key set should work correctly")
    public void keySet_shouldWorkCorrectly() {
        DiskMap diskMap = null;
        try {
            diskMap = new DiskMap("test");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        diskMap.clear();
        Map<String, String> map = new HashMap<>();
        map.put("1", "2");
        map.put("2", "2");
        map.put("3", "2");
        map.put("3", "3");
        diskMap.putAll(map);
        Set<String> expectedSet = Set.of("1", "2", "3");

        Set<String> actualSet = diskMap.keySet();

        assertEquals(expectedSet, actualSet);

    }

    @Test()
    @DisplayName("values should work correctly")
    public void values_shouldWorkCorrectly() {
        DiskMap diskMap = null;
        try {
            diskMap = new DiskMap("test");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        diskMap.clear();
        Map<String, String> map = new HashMap<>();
        map.put("1", "2");
        map.put("2", "2");
        map.put("3", "2");
        map.put("3", "3");
        diskMap.putAll(map);
        Collection<String> expectedCollection = List.of("2", "2", "3");

        Collection<String> actualCollection = diskMap.values();

        assertEquals(expectedCollection, actualCollection);
    }

    @Test()
    @DisplayName("entry set should work correctly")
    public void entrySet_shouldWorkCorrectly() {
        DiskMap diskMap = null;
        try {
            diskMap = new DiskMap("test");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        diskMap.clear();
        Map<String, String> map = new HashMap<>();
        map.put("1", "2");
        map.put("2", "2");
        map.put("3", "2");
        map.put("3", "3");
        diskMap.putAll(map);
        Set<Map.Entry<String, String>> expectedCollection = Set.of(new AbstractMap.SimpleEntry<>("1", "2"),
                new AbstractMap.SimpleEntry<>("2", "2"),
                new AbstractMap.SimpleEntry<>("3", "3")
        );

        @NotNull Set<Map.Entry<String, String>> actualCollection = diskMap.entrySet();

        assertEquals(expectedCollection, actualCollection);
    }
}
