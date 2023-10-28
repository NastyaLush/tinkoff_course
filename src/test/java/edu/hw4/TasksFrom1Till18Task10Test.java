package edu.hw4;

import edu.hw4.given.Animal;
import edu.hw4.tasks1_18.TasksFrom1Till18;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;

public class TasksFrom1Till18Task10Test {

    public static Stream<Arguments> providerTask10() {
        return Stream.of(
                Arguments.of(
                        List.of(new Animal("d", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, false),
                                new Animal("dd", Animal.Type.DOG, Animal.Sex.F, 4, 0, 0, false),
                                new Animal("ddd", Animal.Type.DOG, Animal.Sex.F, 3, 0, 0, false),
                                new Animal("d", Animal.Type.FISH, Animal.Sex.F, 0, 0, 0, false)
                        ), List.of(
                                new Animal("d", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, false),
                                new Animal("ddd", Animal.Type.DOG, Animal.Sex.F, 3, 0, 0, false)
                        )),
                Arguments.of(
                        List.of(new Animal("d", Animal.Type.CAT, Animal.Sex.F, 3, 0, 0, false),
                                new Animal("dd", Animal.Type.SPIDER, Animal.Sex.F, 8, 0, 0, false),
                                new Animal("ddd", Animal.Type.BIRD, Animal.Sex.F, 3, 0, 0, false),
                                new Animal("ddd", Animal.Type.BIRD, Animal.Sex.F, 2, 0, 0, false),
                                new Animal("d", Animal.Type.FISH, Animal.Sex.F, 1, 0, 0, false)
                        ), List.of(
                                new Animal("d", Animal.Type.CAT, Animal.Sex.F, 3, 0, 0, false),
                                new Animal("ddd", Animal.Type.BIRD, Animal.Sex.F, 3, 0, 0, false),
                                new Animal("d", Animal.Type.FISH, Animal.Sex.F, 1, 0, 0, false)
                        )),
                Arguments.of(List.of(), List.of())

        );
    }

    @ParameterizedTest
    @MethodSource("providerTask10")
    public void task10AnimalWhereAgeAndPawsDifferent_shouldReturnAnimalWhereAgeAndPawsDifferent(List<Animal> givenAnimals, List<Animal> expectedAnimals) {
        List<Animal> actualAnimals = new TasksFrom1Till18().task10AnimalWhereAgeAndPawsDifferent(givenAnimals);

        assertArrayEquals(expectedAnimals.toArray(), actualAnimals.toArray());
    }

}
