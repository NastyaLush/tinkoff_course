package edu.hw4;

import edu.hw4.given.Animal;
import edu.hw4.tasks1_18.TasksFrom1Till18;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TasksFrom1Till18Task9Test {

    public static Stream<Arguments> providerTask7() {
        return Stream.of(
                Arguments.of(
                        List.of(new Animal("d", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, false),
                                new Animal("dd", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, false),
                                new Animal("ddd", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, false),
                                new Animal("d", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, false)
                        ), 16),
                Arguments.of(
                        List.of(new Animal("d", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, false),
                                new Animal("dd", Animal.Type.FISH, Animal.Sex.F, 0, 0, 0, false),
                                new Animal("ddd", Animal.Type.SPIDER, Animal.Sex.F, 0, 0, 0, false),
                                new Animal("d", Animal.Type.BIRD, Animal.Sex.F, 0, 0, 0, false)
                        ), 4 + 8 + 2),
                Arguments.of(
                        List.of(new Animal("d", Animal.Type.BIRD, Animal.Sex.F, 0, 0, 0, false),
                                new Animal("dd", Animal.Type.BIRD, Animal.Sex.F, 0, 0, 0, false)
                        ), 2 + 2)
        );
    }

    @ParameterizedTest
    @MethodSource("providerTask7")
    public void task9SumOfPaws_shouldReturnTheOldestAnimal(List<Animal> givenAnimals, Integer expectedSumOfPaws) {

        Integer actualSumOfPaws = new TasksFrom1Till18().task9SumOfPaws(givenAnimals);

        assertEquals(expectedSumOfPaws, actualSumOfPaws);
    }

    @Test
    public void task9SumOfPaws_shouldThrowExceptionIfListIsEmpty() {
        List<Animal> animals = new ArrayList<>();

        assertThrows(Exception.class, () -> new TasksFrom1Till18().task9SumOfPaws(animals));
    }
}
