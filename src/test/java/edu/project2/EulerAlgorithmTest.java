package edu.project2;

import edu.project2.generators.EulerAlgorithm;
import edu.project2.generators.GrowingForestAlgorithm;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EulerAlgorithmTest {

    @Test
    public void generate_shouldCallValidateDataMethod() {
        EulerAlgorithm eulerAlgorithm = Mockito.spy(new EulerAlgorithm());
        eulerAlgorithm.generate(1, 1);
        Mockito.verify(eulerAlgorithm, Mockito.times(1))
               .generate(Mockito.any(), Mockito.any());
    }

}
