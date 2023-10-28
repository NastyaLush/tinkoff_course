package edu.hw4;

import edu.hw4.given.Animal;
import edu.hw4.tasks1_18.TasksFrom1Till18;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TasksFrom1Till18Task5Test {

    public static Stream<Arguments> providerTask5() {
        return Stream.of(
                Arguments.of(
                        List.of(new Animal("d", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, false),
                                new Animal("dd", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, false),
                                new Animal("ddd", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, false),
                                new Animal("d", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, false)
                        ), Animal.Sex.F
                ),
                Arguments.of(
                        List.of(new Animal("d", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, false),
                                new Animal("dd", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, false),
                                new Animal("dd", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, false),
                                new Animal("d", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, false)
                        ), Animal.Sex.M),
                Arguments.of(
                        List.of(new Animal("", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, false),
                                new Animal("dd", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, false),
                                new Animal("ddd", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, false),
                                new Animal("d", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, false)
                        ), Animal.Sex.F)

        );
    }

    @ParameterizedTest
    @MethodSource("providerTask5")
    public void task5TheMostPopularGender_shouldReturnTheMostPopularGender(List<Animal> givenAnimals, Animal.Sex expectedSex) {

        Animal.Sex actualSex = new TasksFrom1Till18().task5TheMostPopularGender(givenAnimals);

        assertEquals(expectedSex, actualSex);
    }

    @Test
    public void task5TheMostPopularGender_shouldWorkCorrectlyIfNoTheMostPopularGender() {
        List<Animal> givenAnimals = new ArrayList<>();
        givenAnimals.add(new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 1, 1, 1, false));
        givenAnimals.add(new Animal("dog", Animal.Type.SPIDER, Animal.Sex.M, 1, 0, 1, false));


        Animal.Sex actualSex = new TasksFrom1Till18().task5TheMostPopularGender(givenAnimals);

        assertNotNull(actualSex);
    }

    @Test
    public void task5TheMostPopularGender_shouldThrowExceptionIfListIsEmpty() {
        List<Animal> animals = new ArrayList<>();

        assertThrows(Exception.class, () -> new TasksFrom1Till18().task5TheMostPopularGender(animals));
    }

}
