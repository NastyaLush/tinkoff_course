package edu.hw4;

import edu.hw4.given.Animal;
import edu.hw4.tasks1_18.TasksFrom1Till18;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class TasksFrom1Till18Task2Test {

    @Test
    public void task2SortAnimalFromHeavyToLight_shouldSortAnimalFromHeavyToLight() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, 1, -1, false));
        animals.add(new Animal("dog", Animal.Type.SPIDER, Animal.Sex.F, 1, 0, 20, false));
        animals.add(new Animal("dog", Animal.Type.FISH, Animal.Sex.F, 1, 23, 2000, false));
        animals.add(new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, -1, 0, false));
        animals.add(new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, 77, -8, false));
        animals.add(new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, 68, 65, false));
        List<Animal> expectedAnimals = new ArrayList<>();
        expectedAnimals.add(new Animal("dog", Animal.Type.FISH, Animal.Sex.F, 1, 23, 2000, false));
        expectedAnimals.add(new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, 68, 65, false));
        expectedAnimals.add(new Animal("dog", Animal.Type.SPIDER, Animal.Sex.F, 1, 0, 20, false));
        expectedAnimals.add(new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, -1, 0, false));
        expectedAnimals.add(new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, 1, -1, false));
        expectedAnimals.add(new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, 77, -8, false));

        List<Animal> actualSortedAnimals = new TasksFrom1Till18().task2SortAnimalFromHeavyToLight(animals);

        assertArrayEquals(expectedAnimals.toArray(), actualSortedAnimals.toArray());
    }

    @Test
    public void task2SortAnimalFromHeavyToLight_shouldCorrectlyWorkWithEmptyList() {
        List<Animal> animals = new ArrayList<>();
        List<Animal> expectedAnimals = new ArrayList<>();

        List<Animal> actualSortedAnimals = new TasksFrom1Till18().task2SortAnimalFromHeavyToLight(animals);

        assertArrayEquals(expectedAnimals.toArray(), actualSortedAnimals.toArray());
    }


}
