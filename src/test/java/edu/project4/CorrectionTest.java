package edu.project4;

import edu.project4.postProcess.Correction;
import edu.project4.structures.FractalImage;
import edu.project4.structures.Pixel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CorrectionTest {

    @Test
    public void process_shouldCorrectlyApplyGammaCorrection() {
        FractalImage givenImage = createTestImage();
        FractalImage expectedImage = createExpectedImage();
        Correction.process(givenImage, 2.2);

        assertArrayEquals(expectedImage.data(), givenImage.data());
    }

    private FractalImage createTestImage() {

        Pixel[][] pixels = new Pixel[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Pixel pixel = new Pixel(row, col, 5); // Adjust RGB values as needed
                pixel.setHitCount(row * col); // Set hit count for testing
                pixels[row][col] = pixel;
            }
        }

        return new FractalImage(pixels, 3, 3);
    }

    private FractalImage createExpectedImage() {

        Pixel[][] pixels = new Pixel[3][3];
        pixels[0][0] = new Pixel(0, 0, 0, 0, 0);
        pixels[0][1] = new Pixel(0, 0, 0, 0, 0);
        pixels[0][2] = new Pixel(0, 0, 0, 0, 0);
        pixels[1][0] = new Pixel(0, 0, 0, 0, 0);
        pixels[1][1] = new Pixel(0, 0, 0, 1, 0);
        pixels[1][2] = new Pixel(0, 1, 3, 2, 0.5);
        pixels[2][0] = new Pixel(0, 0, 0, 0, 0);
        pixels[2][1] = new Pixel(1, 0, 3, 2, 0.5);
        pixels[2][2] = new Pixel(2, 2, 5, 4, 1.0);

        return new FractalImage(pixels, 3, 3);
    }
}
