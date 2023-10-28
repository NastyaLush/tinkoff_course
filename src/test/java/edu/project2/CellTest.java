package edu.project2;

import edu.project2.gameObjects.Cell;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class CellTest {

    public static Stream<Arguments> cellProvider() {
        return Stream.of(
                Arguments.of(-1, 1),
                Arguments.of(-1, 0)
        );
    }


    @ParameterizedTest
    @MethodSource("cellProvider")
    public void cell_shouldThrowExceptionIfDataIncorrect(Integer row, Integer column) {
        assertThrows(IllegalArgumentException.class, () -> new Cell(row, column, true, true));
    }

    @Test
    public void cell_shouldWorkCorrectlyIfDataCorrect() {
        new Cell(0, 0, false, false);
    }

}
