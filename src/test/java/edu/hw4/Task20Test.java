package edu.hw4;

import edu.hw4.given.Animal;
import edu.hw4.task19.Task19;
import edu.hw4.task19.ValidationError;
import edu.hw4.task20.Task20;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Task20Test {

    @Test
    public void animalsThatHaveErrorsWithPrettyPrint_shouldReturnAnimalsWithStringOfValidationErrors() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, 1, 1, false));
        animals.add(new Animal("dogd", Animal.Type.DOG, Animal.Sex.F, 1, 1, 1, false));
        animals.add(new Animal("dog", null, null, 0, Integer.MIN_VALUE, -1, false));
        animals.add(new Animal(null, Animal.Type.BIRD, Animal.Sex.F, 1, 77, 8, false));
        Map<String, String> expectedMap = new HashMap<>();

        expectedMap.put("dog", "name, weight, height, sex, type, age");
        expectedMap.put(null, "name");

        Map<String, String> actualMap = new Task20().animalsThatHaveErrorsWithPrettyPrint(animals);

        expectedMap.forEach((s, validationErrors) -> assertArrayEquals(Arrays.stream(validationErrors.split(", "))
                                                                             .sorted()
                                                                             .toArray(), Arrays.stream(actualMap.get(s)
                                                                                                                .split(", "))
                                                                                               .sorted()
                                                                                               .toArray()));
    }

    @Test
    public void getAnimalsThatHaveErrors_shouldCorrectlyWorkWithEmptyList() {
        List<Animal> animals = new ArrayList<>();
        Map<String, Set<ValidationError>> expectedMap = new HashMap<>();


        Map<String, Set<ValidationError>> actualMap = new Task19().getAnimalsThatHaveErrors(animals);

        assertTrue(expectedMap.equals(actualMap));
    }

}
