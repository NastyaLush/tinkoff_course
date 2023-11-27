package edu.hw4;

import edu.hw4.given.Animal;
import edu.hw4.tasks1_18.TasksFrom1Till18;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;

public class TasksFrom1Till18Task13Test {

    public static Stream<Arguments> providerTask13() {
        return Stream.of(
                Arguments.of(
                        List.of(new Animal("d d", Animal.Type.DOG, Animal.Sex.F, 0, 100, 0, false),
                                new Animal("dd1d", Animal.Type.DOG, Animal.Sex.F, 4, 101, 0, false),
                                new Animal("", Animal.Type.DOG, Animal.Sex.F, 3, 100, 0, true),
                                new Animal("ddddd", Animal.Type.FISH, Animal.Sex.F, 0, 101, 0, true),
                                new Animal("dd dd d", Animal.Type.FISH, Animal.Sex.F, 0, 101, 0, true),
                                new Animal("d 8 8", Animal.Type.FISH, Animal.Sex.F, 0, 101, 0, true),
                                new Animal("8 8", Animal.Type.FISH, Animal.Sex.F, 0, 101, 0, true)
                        ), List.of(
                                new Animal("dd dd d", Animal.Type.FISH, Animal.Sex.F, 0, 101, 0, true),
                                new Animal("d 8 8", Animal.Type.FISH, Animal.Sex.F, 0, 101, 0, true)
                        )),
                Arguments.of(List.of(), List.of())

        );
    }

    @ParameterizedTest
    @MethodSource("providerTask13")
    public void task13AnimalWhichNameMoreThanTwoWords_shouldReturnAnimalWhichNameMoreThanTwoWords(List<Animal> givenAnimals, List<Animal> expectedAnimals) {
        List<Animal> actualAnimals = new TasksFrom1Till18().task13AnimalWhichNameMoreThanTwoWords(givenAnimals);

        assertArrayEquals(expectedAnimals.toArray(), actualAnimals.toArray());
    }

}
