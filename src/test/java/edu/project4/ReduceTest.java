package edu.project4;

import edu.project4.postProcess.Reduce;
import edu.project4.structures.FractalImage;
import edu.project4.structures.Pixel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ReduceTest {

    @Test
    public void process_shouldCorrectlyApplyReduce() {
        FractalImage givenImage = createTestImage();
        FractalImage expectedImage = createExpectedImage();
        givenImage = Reduce.process(givenImage, 2);

        assertArrayEquals(expectedImage.data(), givenImage.data());
    }

    private FractalImage createTestImage() {

        Pixel[][] pixels = new Pixel[4][4];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Pixel pixel = new Pixel(row, col, 5); // Adjust RGB values as needed
                pixel.setHitCount(row * col); // Set hit count for testing
                pixels[row][col] = pixel;
            }
        }

        return new FractalImage(pixels, 4, 4);
    }

    private FractalImage createExpectedImage() {

        Pixel[][] pixels = new Pixel[2][2];
        pixels[0][0] = new Pixel(0, 0, 5, 1, 0);
        pixels[0][1] = new Pixel(0, 2, 5, 5, 0);
        pixels[1][0] = new Pixel(2, 0, 5, 5, 0);
        pixels[1][1] = new Pixel(2, 2, 5, 25, 0);

        return new FractalImage(pixels, 2, 2);
    }
}
