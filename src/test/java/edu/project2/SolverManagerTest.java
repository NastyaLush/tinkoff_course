package edu.project2;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import edu.project2.solvers.Solver;
import edu.project2.solvers.SolverManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.HashSet;
import java.util.Set;

public class SolverManagerTest {


    @Test
    public void solveMaze_shouldCallSolveMethod() {
        Solver solver = Mockito.mock(Solver.class);
        Cell begin = Mockito.mock(Cell.class);
        Cell end = Mockito.mock(Cell.class);
        Cell randomCell = Mockito.mock(Cell.class);
        Set<Cell> givenSetCell = new HashSet<>();
        givenSetCell.add(begin);
        givenSetCell.add(randomCell);
        givenSetCell.add(end);
        Maze<Cell> maze = new Maze<>(new Cell[][]{}, 0, 0);
        SolverManager solverManager = new SolverManager(maze);
        Mockito.when(solver.solve(maze, begin, end))
               .thenReturn(givenSetCell);

        solverManager.solveMaze(solver, begin, end);

        Mockito.verify(solver, Mockito.times(1))
               .solve(maze, begin, end);
    }


}
