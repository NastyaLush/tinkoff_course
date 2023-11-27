package edu.project2;

import edu.project2.generators.RecursiveBacktracker;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RecursiveBacktrackerTest {

    @Test
    public void generate_shouldCallValidateDataMethod() {
        RecursiveBacktracker recursiveBacktracker = Mockito.spy(new RecursiveBacktracker());
        recursiveBacktracker.generate(1, 1);
        Mockito.verify(recursiveBacktracker, Mockito.times(1))
               .generate(Mockito.any(), Mockito.any());
    }

}
