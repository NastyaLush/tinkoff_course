package edu.project4;

import edu.project4.structures.FractalImage;
import edu.project4.structures.Pixel;
import edu.project4.structures.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FractalImageTest {

    @Test
    public void testPixelByCoordinates() {
        FractalImage fractalImage = FractalImage.create(3, 3);
        int x = 1;
        int y = 2;

        Pixel pixel = fractalImage.pixel(x, y);

        assertNotNull(pixel);
        assertEquals(pixel, fractalImage.data()[x][y]);
    }

    @Test
    public void testPixelByPoint() {
        FractalImage fractalImage = FractalImage.create(3, 3);
        Point point = new Point(1.5, 2.5);

        Pixel pixel = fractalImage.pixel(point);

        assertNotNull(pixel);
        assertEquals(pixel, fractalImage.data()[(int) point.x()][(int) point.y()]);
    }

    @Test
    public void testIsInImage() {
        FractalImage fractalImage = FractalImage.create(4, 4);

        assertTrue(fractalImage.isInImage(new Point(1, 1)));
        assertTrue(fractalImage.isInImage(new Point(3, 3)));

        assertFalse(fractalImage.isInImage(new Point(-1, 2)));
        assertFalse(fractalImage.isInImage(new Point(4, 4)));
        assertFalse(fractalImage.isInImage(new Point(2, -1)));
        assertFalse(fractalImage.isInImage(new Point(0, 5)));
    }
}
