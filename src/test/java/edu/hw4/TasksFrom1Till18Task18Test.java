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

public class TasksFrom1Till18Task18Test {

    public static Stream<Arguments> providerTask18() {
        return Stream.of(
                Arguments.of(List.of(
                                List.of(new Animal("d", Animal.Type.FISH, Animal.Sex.F, 0, 0, 0, false),
                                        new Animal("dd", Animal.Type.FISH, Animal.Sex.F, 0, 0, 2, false),
                                        new Animal("ddd", Animal.Type.DOG, Animal.Sex.F, 0, 0, 2222220, false),
                                        new Animal("d", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, false)
                                ),
                                List.of(new Animal("d", Animal.Type.FISH, Animal.Sex.F, 0, 0, -1, false),
                                        new Animal("dd", Animal.Type.FISH, Animal.Sex.F, 0, 0, 22, false)),
                                List.of()),
                        new Animal("dd", Animal.Type.FISH, Animal.Sex.F, 0, 0, 22, false)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("providerTask18")
    public void task18TheMostHeavyFish_shouldReturnTheMostHeavyFish(List<List<Animal>> givenAnimals, Animal expectedAnimal) {

        Animal actualAnimal = new TasksFrom1Till18().task18TheMostHeavyFish(givenAnimals);

        assertEquals(expectedAnimal, actualAnimal);
    }

    @Test
    public void task18TheMostHeavyFish_shouldThrowExceptionIfListIsEmpty() {
        List<List<Animal>> animals = new ArrayList<>();

        assertThrows(Exception.class, () -> new TasksFrom1Till18().task18TheMostHeavyFish(animals));
    }


}
