package edu.hw4;

import edu.hw4.given.Animal;
import edu.hw4.tasks1_18.TasksFrom1Till18;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;

public class TasksFrom1Till18Task12Test {

    public static Stream<Arguments> providerTask12() {
        return Stream.of(
                Arguments.of(
                        List.of(new Animal("d", Animal.Type.DOG, Animal.Sex.F, 0, 100, 100, false),
                                new Animal("dd", Animal.Type.DOG, Animal.Sex.F, 4, 100, 101, false),
                                new Animal("ddd", Animal.Type.DOG, Animal.Sex.F, 3, 100, 99, true),
                                new Animal("d", Animal.Type.FISH, Animal.Sex.F, 0, 101, 0, true)
                        ), 1),
                Arguments.of(
                        List.of(new Animal("d", Animal.Type.CAT, Animal.Sex.F, 3, 101, 0, false),
                                new Animal("dd", Animal.Type.SPIDER, Animal.Sex.F, 8, 101, 0, false)
                        ), 0),
                Arguments.of(List.of(), 0)

        );
    }

    @ParameterizedTest
    @MethodSource("providerTask12")
    public void task12AnimalWhichWeightMoreThanHeight_shouldReturnAnimalWhichWeightMoreThanHeight(List<Animal> givenAnimals, Integer expectedAnswer) {
        Integer actualAnswer = new TasksFrom1Till18().task12AnimalWhichWeightMoreThanHeight(givenAnimals);

        assertEquals(expectedAnswer, actualAnswer);
    }

}
