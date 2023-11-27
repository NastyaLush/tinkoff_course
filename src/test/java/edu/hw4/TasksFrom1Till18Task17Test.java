package edu.hw4;

import edu.hw4.given.Animal;
import edu.hw4.tasks1_18.TasksFrom1Till18;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;

public class TasksFrom1Till18Task17Test {

    public static Stream<Arguments> providerTask17() {
        return Stream.of(
                Arguments.of(
                        List.of(new Animal("d", Animal.Type.DOG, Animal.Sex.F, 0, 4, 0, false),
                                new Animal("d", Animal.Type.DOG, Animal.Sex.F, 4, 5, 0, false),
                                new Animal("d", Animal.Type.DOG, Animal.Sex.F, 3, 6, 0, true),
                                new Animal("d", Animal.Type.FISH, Animal.Sex.F, 7, 101, 0, true),
                                new Animal("d", Animal.Type.FISH, Animal.Sex.F, 4, 101, 0, true),
                                new Animal("d", Animal.Type.FISH, Animal.Sex.F, 8, 101, 0, true),
                                new Animal("d", Animal.Type.FISH, Animal.Sex.F, 10, 101, 0, true)
                        ), false),
                Arguments.of(
                        List.of(new Animal("d", Animal.Type.DOG, Animal.Sex.F, 0, 4, 0, false),
                                new Animal("d", Animal.Type.DOG, Animal.Sex.F, 4, 5, 0, false),
                                new Animal("d", Animal.Type.DOG, Animal.Sex.F, 3, 6, 0, true),
                                new Animal("d", Animal.Type.SPIDER, Animal.Sex.F, 7, 101, 0, true),
                                new Animal("d", Animal.Type.SPIDER, Animal.Sex.F, 4, 101, 0, false),
                                new Animal("d", Animal.Type.FISH, Animal.Sex.F, 8, 101, 0, true),
                                new Animal("d", Animal.Type.FISH, Animal.Sex.F, 10, 101, 0, true)
                        ), false),
                Arguments.of(
                        List.of(new Animal("d", Animal.Type.DOG, Animal.Sex.F, 0, 4, 0, false),
                                new Animal("d", Animal.Type.DOG, Animal.Sex.F, 4, 5, 0, false),
                                new Animal("d", Animal.Type.DOG, Animal.Sex.F, 3, 6, 0, true),
                                new Animal("d", Animal.Type.SPIDER, Animal.Sex.F, 7, 101, 0, true),
                                new Animal("d", Animal.Type.SPIDER, Animal.Sex.F, 4, 101, 0, true),
                                new Animal("d", Animal.Type.FISH, Animal.Sex.F, 8, 101, 0, true),
                                new Animal("d", Animal.Type.FISH, Animal.Sex.F, 10, 101, 0, true)
                        ), true),
                Arguments.of(List.of(), false)

        );
    }

    @ParameterizedTest
    @MethodSource("providerTask17")
    public void task17SpidersBiteMoreThanDogs_shouldReturnSpidersBiteMoreThanDogs(List<Animal> givenAnimals, boolean expectedAnswer) {
        boolean actualAnswer = new TasksFrom1Till18().task17SpidersBiteMoreThanDogs(givenAnimals);

        assertEquals(expectedAnswer, actualAnswer);
    }

}
