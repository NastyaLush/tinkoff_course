package edu.hw4;

import edu.hw4.given.Animal;
import edu.hw4.tasks1_18.TasksFrom1Till18;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;

public class TasksFrom1Till18Task15Test {

    public static Stream<Arguments> providerTask15() {
        return Stream.of(
                Arguments.of(
                        List.of(new Animal("d", Animal.Type.DOG, Animal.Sex.F, 4, 100, 100, false),
                                new Animal("dd", Animal.Type.DOG, Animal.Sex.F, 5, 5, 101, false),
                                new Animal("ddd", Animal.Type.DOG, Animal.Sex.F, 6, 6, 99, true),
                                new Animal("d", Animal.Type.FISH, Animal.Sex.F, 7, 7, 0, true)
                        ), 5, 6, 101 + 99),
                Arguments.of(
                        List.of(new Animal("d", Animal.Type.CAT, Animal.Sex.F, 3, 101, 0, false),
                                new Animal("dd", Animal.Type.SPIDER, Animal.Sex.F, 8, 101, 0, false)
                        ), 9, 20, 0),
                Arguments.of(List.of(), 0, 0, 0)

        );
    }

    @ParameterizedTest
    @MethodSource("providerTask15")
    public void task15WeightOfAnimalsOlderMinAgeYoungerMaxAge_shouldReturnWeightOfAnimalsOlderMinAgeYoungerMaxAge(List<Animal> givenAnimals, Integer minAge, Integer maxAge, Integer expectedAnswer) {
        Integer actualAnswer = new TasksFrom1Till18().task15WeightOfAnimalsOlderMinAgeYoungerMaxAge(givenAnimals, minAge, maxAge);

        assertEquals(expectedAnswer, actualAnswer);
    }

}
