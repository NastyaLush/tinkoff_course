package edu.hw4;

import edu.hw4.given.Animal;
import edu.hw4.tasks1_18.TasksFrom1Till18;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class TasksFrom1Till18Task1Test {

    @Test
    public void task1SortAnimalFromShortToTall_shouldSortAnimalFromShortToTaLL() {
        //given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, 1, 1, false));
        animals.add(new Animal("dog", Animal.Type.SPIDER, Animal.Sex.F, 1, 0, 1, false));
        animals.add(new Animal("dog", Animal.Type.FISH, Animal.Sex.F, 1, 23, 1, false));
        animals.add(new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, -1, 1, false));
        animals.add(new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, 77, 1, false));
        animals.add(new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, 68, 1, false));

        List<Animal> expectedAnimals = new ArrayList<>();
        expectedAnimals.add(new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, -1, 1, false));
        expectedAnimals.add(new Animal("dog", Animal.Type.SPIDER, Animal.Sex.F, 1, 0, 1, false));
        expectedAnimals.add(new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, 1, 1, false));
        expectedAnimals.add(new Animal("dog", Animal.Type.FISH, Animal.Sex.F, 1, 23, 1, false));
        expectedAnimals.add(new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, 68, 1, false));
        expectedAnimals.add(new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, 77, 1, false));

        //when
        List<Animal> actualSortedAnimals = new TasksFrom1Till18().task1SortAnimalFromShortToTall(animals);
        //then
        assertArrayEquals(expectedAnimals.toArray(), actualSortedAnimals.toArray());
    }

    @Test
    public void task1SortAnimalFromShortToTall_shouldCorrectlyWorkWithEmptyList() {
        List<Animal> animals = new ArrayList<>();
        List<Animal> expectedAnimals = new ArrayList<>();

        List<Animal> actualSortedAnimals = new TasksFrom1Till18().task1SortAnimalFromShortToTall(animals);

        assertArrayEquals(expectedAnimals.toArray(), actualSortedAnimals.toArray());
    }

}
