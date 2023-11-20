package edu.hw4;

import edu.hw4.given.Animal;
import edu.hw4.tasks1_18.TasksFrom1Till18;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TasksFrom1Till18Task3Test {


    @Test
    public void task3AnimalToType_shouldSortByType() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, 1, -1, false));
        animals.add(new Animal("dog", Animal.Type.FISH, Animal.Sex.F, 1, 23, 2000, false));
        animals.add(new Animal("dog", Animal.Type.CAT, Animal.Sex.F, 1, -1, 0, false));
        animals.add(new Animal("dog", Animal.Type.BIRD, Animal.Sex.F, 1, 77, -8, false));
        animals.add(new Animal("dog", Animal.Type.FISH, Animal.Sex.F, 1, 68, 65, false));
        animals.add(new Animal("dog", Animal.Type.FISH, Animal.Sex.F, 1, 68, 65, false));
        Map<Animal.Type, Long> expectedAnimalsToType = new HashMap<>();
        expectedAnimalsToType.put(Animal.Type.BIRD, 1L);
        expectedAnimalsToType.put(Animal.Type.DOG, 1L);
        expectedAnimalsToType.put(Animal.Type.FISH, 3L);
        expectedAnimalsToType.put(Animal.Type.CAT, 1L);

        Map<Animal.Type, Long> actualAnimalsToType = new TasksFrom1Till18().task3AnimalToType(animals);

        assertTrue(expectedAnimalsToType.equals(actualAnimalsToType));
    }

    @Test
    public void task3AnimalToType_shouldCorrectlyWorkWithEmptyList() {
        List<Animal> animals = new ArrayList<>();
        Map<Animal.Type, Long> expectedAnimalsToType = new HashMap<>();

        Map<Animal.Type, Long> actualAnimalsToType = new TasksFrom1Till18().task3AnimalToType(animals);

        assertTrue(expectedAnimalsToType.equals(actualAnimalsToType));
    }


}


