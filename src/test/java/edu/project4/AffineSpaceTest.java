package edu.project4;

import edu.project4.structures.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AffineSpaceTest {

    @Test
    public void apply_shouldCorrectlyApplyChanges() {
        AffineSpace affineSpace = new AffineSpace(1, 0, 0, 0, 1, 0);
        Point point = new Point(2, 3);
        Point result = affineSpace.apply(point);

        assertEquals(2, result.x());
        assertEquals(3, result.y());
    }

    @Test
    public void affineSpaceNoArgsConstructor_shouldCorrectlyGenerateAffineSpace() {
        AffineSpace affineSpace = new AffineSpace();

        assertTrue((affineSpace.getA() * affineSpace.getA() + affineSpace.getD() * affineSpace.getD()) <= 1);
        assertTrue((affineSpace.getB() * affineSpace.getB() + affineSpace.getE() * affineSpace.getE()) <= 1);
        assertTrue((affineSpace.getA() * affineSpace.getA() + affineSpace.getB() * affineSpace.getB()
            + affineSpace.getD() * affineSpace.getD() + affineSpace.getE() * affineSpace.getE())
                       <= (1 + (affineSpace.getA() * affineSpace.getE() - affineSpace.getD() * affineSpace.getB())
            * (affineSpace.getA() * affineSpace.getE() - affineSpace.getD() * affineSpace.getB())));
    }

    @Test
    public void parameterizedAffineSpaceConstructor_shouldCorrectlyCreateObject() {
        double red = 0.5;
        double green = 0.3;
        double blue = 0.7;
        double a = 1.2;
        double b = -0.5;
        double c = 0.8;
        double d = 0.4;
        double e = 0.9;
        double f = -0.3;

        AffineSpace affineSpace = new AffineSpace(red, green, blue, a, b, c, d, e, f);

        assertEquals((int) (red * 256), affineSpace.getRed());
        assertEquals((int) (green * 256), affineSpace.getGreen());
        assertEquals((int) (blue * 256), affineSpace.getBlue());
        assertEquals(a, affineSpace.getA());
        assertEquals(b, affineSpace.getB());
        assertEquals(c, affineSpace.getC());
        assertEquals(d, affineSpace.getD());
        assertEquals(e, affineSpace.getE());
        assertEquals(f, affineSpace.getF());
    }
}
