package edu.project2;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class MazeTest {

    public static Stream<Arguments> mazeProvider() {
        return Stream.of(
                Arguments.of(
                        new Cell[][]{}, 0, 0
                ),
                Arguments.of(
                        new Cell[][]{}, -1, 1
                ),
                Arguments.of(
                        new Cell[][]{}, 1, 1
                ),
                Arguments.of(
                        new Cell[][]{new Cell[]{new Cell(1, 1, false, false)}}, 0, 0
                ),
                Arguments.of(
                        new Cell[][]{new Cell[]{new Cell(1, 1, false, false)}}, 1, 2
                )
        );
    }


    @ParameterizedTest
    @MethodSource("mazeProvider")
    public void maze_shouldThrowExceptionIfDataIncorrect(Cell[][] mazeArray, Integer rows, Integer columns) {
        assertThrows(IllegalArgumentException.class, () -> new Maze<>(mazeArray, rows, columns));
    }

    @Test
    public void maze_shouldWorkCorrectlyIfDataCorrect() {
        new Maze<>(new Cell[][]{new Cell[]{new Cell(1, 1, false, false)},
                new Cell[]{new Cell(1, 1, false, false)}}, 2, 1);

    }
}
