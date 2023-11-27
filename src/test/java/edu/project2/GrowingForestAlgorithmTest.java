package edu.project2;

import edu.project2.generators.GrowingForestAlgorithm;
import edu.project2.generators.RecursiveBacktracker;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GrowingForestAlgorithmTest {

    @Test
    public void generate_shouldCallValidateDataMethod() {
        GrowingForestAlgorithm growingForestAlgorithm = Mockito.spy(new GrowingForestAlgorithm());
        growingForestAlgorithm.generate(1, 1);
        Mockito.verify(growingForestAlgorithm, Mockito.times(1))
               .generate(Mockito.any(), Mockito.any());
    }
}
