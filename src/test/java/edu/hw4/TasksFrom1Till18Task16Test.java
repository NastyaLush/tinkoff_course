package edu.hw4;

import edu.hw4.given.Animal;
import edu.hw4.tasks1_18.TasksFrom1Till18;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;

public class TasksFrom1Till18Task16Test {

    public static Stream<Arguments> providerTask16() {
        return Stream.of(
                Arguments.of(
                        List.of(new Animal("s", Animal.Type.DOG, Animal.Sex.F, 0, 100, 0, false),
                                new Animal("88", Animal.Type.BIRD, Animal.Sex.F, 0, 101, 0, true),
                                new Animal("d", Animal.Type.DOG, Animal.Sex.M, 4, 101, 0, false),
                                new Animal("", Animal.Type.DOG, Animal.Sex.M, 3, 100, 0, true),
                                new Animal("d", Animal.Type.FISH, Animal.Sex.F, 0, 101, 0, true),
                                new Animal("ddd", Animal.Type.FISH, Animal.Sex.M, 0, 101, 0, true),
                                new Animal("sd", Animal.Type.FISH, Animal.Sex.F, 0, 101, 0, true),
                                new Animal("88", Animal.Type.FISH, Animal.Sex.F, 0, 101, 0, true),
                                new Animal("88", Animal.Type.CAT, Animal.Sex.M, 0, 101, 0, true),
                                new Animal("88", Animal.Type.SPIDER, Animal.Sex.F, 0, 101, 0, true)
                        ), List.of(
                                new Animal("88", Animal.Type.CAT, Animal.Sex.M, 0, 101, 0, true),
                                new Animal("", Animal.Type.DOG, Animal.Sex.M, 3, 100, 0, true),
                                new Animal("d", Animal.Type.DOG, Animal.Sex.M, 4, 101, 0, false),
                                new Animal("s", Animal.Type.DOG, Animal.Sex.F, 0, 100, 0, false),
                                new Animal("88", Animal.Type.BIRD, Animal.Sex.F, 0, 101, 0, true),
                                new Animal("ddd", Animal.Type.FISH, Animal.Sex.M, 0, 101, 0, true),
                                new Animal("88", Animal.Type.FISH, Animal.Sex.F, 0, 101, 0, true),
                                new Animal("d", Animal.Type.FISH, Animal.Sex.F, 0, 101, 0, true),
                                new Animal("sd", Animal.Type.FISH, Animal.Sex.F, 0, 101, 0, true),
                                new Animal("88", Animal.Type.SPIDER, Animal.Sex.F, 0, 101, 0, true)
                        )

                ), Arguments.of(List.of(), List.of()));
    }

    @ParameterizedTest
    @MethodSource("providerTask16")
    public void task16SortedByTypeSexName_shouldReturnAnimalsSortedByTypeSexNames(List<Animal> givenAnimals, List<Animal> expectedAnimals) {
        List<Animal> actualAnimals = new TasksFrom1Till18().task16SortedByTypeSexName(givenAnimals);

        assertArrayEquals(expectedAnimals.toArray(), actualAnimals.toArray());
    }


}
