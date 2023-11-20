package edu.hw4;

import edu.hw4.given.Animal;
import edu.hw4.tasks1_18.TasksFrom1Till18;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;

public class TasksFrom1Till18Task11Test {

    public static Stream<Arguments> providerTask11() {
        return Stream.of(
                Arguments.of(
                        List.of(new Animal("d", Animal.Type.DOG, Animal.Sex.F, 0, 100, 0, false),
                                new Animal("dd", Animal.Type.DOG, Animal.Sex.F, 4, 101, 0, false),
                                new Animal("ddd", Animal.Type.DOG, Animal.Sex.F, 3, 100, 0, true),
                                new Animal("d", Animal.Type.FISH, Animal.Sex.F, 0, 101, 0, true)
                        ), List.of(
                                new Animal("d", Animal.Type.FISH, Animal.Sex.F, 0, 101, 0, true)
                        )),
                Arguments.of(
                        List.of(new Animal("d", Animal.Type.CAT, Animal.Sex.F, 3, 101, 0, false),
                                new Animal("dd", Animal.Type.SPIDER, Animal.Sex.F, 8, 101, 0, false)
                        ), List.of()),
                Arguments.of(List.of(), List.of())

        );
    }

    @ParameterizedTest
    @MethodSource("providerTask11")
    public void task11task11AnimalThatBitsAndTaller100_shouldReturnAnimalThatBitsAndTaller100(List<Animal> givenAnimals, List<Animal> expectedAnimals) {
        List<Animal> actualAnimals = new TasksFrom1Till18().task11AnimalThatBitsAndTaller100(givenAnimals);

        assertArrayEquals(expectedAnimals.toArray(), actualAnimals.toArray());
    }

}
