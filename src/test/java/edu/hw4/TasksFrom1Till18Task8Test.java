package edu.hw4;

import edu.hw4.given.Animal;
import edu.hw4.tasks1_18.TasksFrom1Till18;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TasksFrom1Till18Task8Test {

    public static Stream<Arguments> providerTask8() {
        return Stream.of(
                Arguments.of(
                        List.of(new Animal("d", Animal.Type.DOG, Animal.Sex.F, 0, 10, 0, false),
                                new Animal("dd", Animal.Type.DOG, Animal.Sex.F, 0, 9, 0, false),
                                new Animal("ddd", Animal.Type.DOG, Animal.Sex.F, 0, 9, 0, false),
                                new Animal("d", Animal.Type.DOG, Animal.Sex.F, 0, 7, 0, false)
                        ), 10, Optional.of(new Animal("dd", Animal.Type.DOG, Animal.Sex.F, 0, 9, 0, false)
                        )),
                Arguments.of(
                        List.of(new Animal("d", Animal.Type.DOG, Animal.Sex.F, 0, 10, 0, false),
                                new Animal("dd", Animal.Type.DOG, Animal.Sex.F, 0, 9, 4, false),
                                new Animal("ddd", Animal.Type.DOG, Animal.Sex.F, 0, 9, 4, false),
                                new Animal("d", Animal.Type.DOG, Animal.Sex.F, 0, 7, 3, false)
                        ), 10, Optional.of(new Animal("dd", Animal.Type.DOG, Animal.Sex.F, 0, 9, 4, false)
                        )),
                Arguments.of(
                        List.of(new Animal("d", Animal.Type.DOG, Animal.Sex.F, 0, 10, 0, false),
                                new Animal("dd", Animal.Type.DOG, Animal.Sex.F, 0, 9, 4, false),
                                new Animal("ddd", Animal.Type.DOG, Animal.Sex.F, 0, 9, 5, false),
                                new Animal("d", Animal.Type.DOG, Animal.Sex.F, 0, 7, 3, false)
                        ), 10, Optional.of(new Animal("ddd", Animal.Type.DOG, Animal.Sex.F, 0, 9, 5, false)
                        )),
                Arguments.of(List.of(), 10, Optional.empty())

        );
    }

    @ParameterizedTest
    @MethodSource("providerTask8")
    public void task8TheMostHeavyAnimalShorterK_shouldReturnTheMostHeavyAnimalShorterK(List<Animal> givenAnimals, Integer k, Optional expectedOptional) {

        Optional<Animal> actualOptional = new TasksFrom1Till18().task8TheMostHeavyAnimalShorterMaxHeight(givenAnimals, k);

        assertEquals(expectedOptional, actualOptional);
    }


}
