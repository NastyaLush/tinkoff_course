package edu.project2;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import edu.project2.generators.Generator;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class GeneratorTest {

    public static Stream<Arguments> validateDataProvider() {
        return Stream.of(
                Arguments.of(-1, 1),
                Arguments.of(-1, 0),
                Arguments.of(0, 0),
                Arguments.of(0, 1)
        );
    }


    @ParameterizedTest
    @MethodSource("validateDataProvider")
    public void validateData_shouldThrowExceptionIfDataIncorrect(Integer row, Integer column) {
        assertThrows(IllegalArgumentException.class, () -> ((Generator) (rows, columns) -> null).validateData(row, column));
    }

    @Test
    public void validateData_shouldWorkCorrectlyIfDataCorrect() {
        ((Generator) (rows, columns) -> null).validateData(1, 1);
    }


}
