package edu.hw4;

import edu.hw4.given.Animal;
import edu.hw4.tasks1_18.TasksFrom1Till18;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TasksFrom1Till18Task6Test {

    @Test
    public void task6TheMostHeavyAnimalByType_shouldFindTheMostHeavyAnimalByType() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, 1, -1, false));
        animals.add(new Animal("dog", Animal.Type.BIRD, Animal.Sex.F, 1, 77, 8, false));
        animals.add(new Animal("dog", Animal.Type.FISH, Animal.Sex.F, 1, 23, 2000, false));
        animals.add(new Animal("dog", Animal.Type.FISH, Animal.Sex.F, 1, 68, 65, false));
        animals.add(new Animal("dog", Animal.Type.FISH, Animal.Sex.F, 1, 68, 65, false));
        animals.add(new Animal("dog", Animal.Type.CAT, Animal.Sex.F, 1, -1, 0, false));
        animals.add(new Animal("dog", Animal.Type.CAT, Animal.Sex.F, 1, -1, 0, false));
        animals.add(new Animal("dog", Animal.Type.CAT, Animal.Sex.F, 1, -1, -1, false));
        Map<Animal.Type, Animal> expectedAnimalsTheMostHeavyByType = new HashMap<>();
        expectedAnimalsTheMostHeavyByType.put(Animal.Type.DOG, new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, 1, -1, false));
        expectedAnimalsTheMostHeavyByType.put(Animal.Type.BIRD, new Animal("dog", Animal.Type.BIRD, Animal.Sex.F, 1, 77, 8, false));
        expectedAnimalsTheMostHeavyByType.put(Animal.Type.FISH, new Animal("dog", Animal.Type.FISH, Animal.Sex.F, 1, 23, 2000, false));
        expectedAnimalsTheMostHeavyByType.put(Animal.Type.CAT, new Animal("dog", Animal.Type.CAT, Animal.Sex.F, 1, -1, 0, false));

        Map<Animal.Type, Animal> actualAnimalsTheMostHeavyByType = new TasksFrom1Till18().task6TheMostHeavyAnimalByType(animals);

        assertTrue(expectedAnimalsTheMostHeavyByType.equals(actualAnimalsTheMostHeavyByType));
    }

    @Test
    public void task6TheMostHeavyAnimalByType_shouldCorrectlyWorkWithEmptyList() {
        List<Animal> animals = new ArrayList<>();
        Map<Animal.Type, Animal> expectedAnimalsTheMostHeavyByType = new HashMap<>();

        Map<Animal.Type, Animal> actualAnimalsTheMostHeavyByType = new TasksFrom1Till18().task6TheMostHeavyAnimalByType(animals);

        assertTrue(expectedAnimalsTheMostHeavyByType.equals(actualAnimalsTheMostHeavyByType));
    }


}
