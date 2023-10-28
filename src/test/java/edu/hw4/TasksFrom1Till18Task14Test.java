package edu.hw4;

import edu.hw4.given.Animal;
import edu.hw4.tasks1_18.TasksFrom1Till18;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;

public class TasksFrom1Till18Task14Test {

    public static Stream<Arguments> providerTask14() {
        return Stream.of(
                Arguments.of(
                        List.of(new Animal("d d", Animal.Type.DOG, Animal.Sex.F, 0, 4, 0, false),
                                new Animal("dd1d", Animal.Type.DOG, Animal.Sex.F, 4, 5, 0, false),
                                new Animal("", Animal.Type.DOG, Animal.Sex.F, 3, 6, 0, true),
                                new Animal("ddddd", Animal.Type.FISH, Animal.Sex.F, 7, 101, 0, true),
                                new Animal("dd dd d", Animal.Type.FISH, Animal.Sex.F, 4, 101, 0, true),
                                new Animal("d 8 8", Animal.Type.FISH, Animal.Sex.F, 8, 101, 0, true),
                                new Animal("8 8", Animal.Type.FISH, Animal.Sex.F, 10, 101, 0, true)
                        ), 5, true),
                Arguments.of(
                        List.of(new Animal("d d", Animal.Type.DOG, Animal.Sex.F, 0, 4, 0, false),
                                new Animal("dd1d", Animal.Type.DOG, Animal.Sex.F, 4, 5, 0, false),
                                new Animal("ddddd", Animal.Type.FISH, Animal.Sex.F, 7, 101, 0, true),
                                new Animal("dd dd d", Animal.Type.FISH, Animal.Sex.F, 4, 101, 0, true),
                                new Animal("d 8 8", Animal.Type.FISH, Animal.Sex.F, 8, 101, 0, true),
                                new Animal("8 8", Animal.Type.FISH, Animal.Sex.F, 10, 101, 0, true)
                        ), 5, false),
                Arguments.of(List.of(), Integer.MIN_VALUE, false)

        );
    }

    @ParameterizedTest
    @MethodSource("providerTask14")
    public void task14isExistDogTallerMinTall_shouldReturnIsExistDogTallerMinTall(List<Animal> givenAnimals, Integer minTall, boolean expectedAnswer) {
        boolean actualAnswer = new TasksFrom1Till18().task14isExistDogTallerMinTall(givenAnimals, minTall);

        assertEquals(expectedAnswer, actualAnswer);
    }

}
