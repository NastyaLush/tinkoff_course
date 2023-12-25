package edu.project4;

import edu.project4.save.ImageFormat;
import edu.project4.save.ImageUtils;
import edu.project4.structures.FractalImage;
import edu.project4.structures.Pixel;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImageUtilsFormatTest {

    @BeforeAll
    public static void createTemporaryDir() throws IOException {
        Path tempDir = Files.createDirectory(Path.of("testDir"));
    }

    @Test
    public void save_shouldSaveCorrectlyPng() throws IOException {
        FractalImage image = createTestImage();
        ImageFormat format = ImageFormat.PNG;

        ImageUtils.save(image, Path.of("img.png"), format);

        assertTrue(Files.exists(Path.of("img.png")));
    }

    @Test
    public void save_shouldSaveCorrectlyBmp() throws IOException {
        FractalImage image = createTestImage();
        ImageFormat format = ImageFormat.BMP;

        ImageUtils.save(image, Path.of("img.bmp"), format);

        assertTrue(Files.exists(Path.of("img.bmp")));
    }

    @Test
    public void save_shouldSaveCorrectlyJpeg() throws IOException {
        FractalImage image = createTestImage();
        ImageFormat format = ImageFormat.JPEG;

        ImageUtils.save(image, Path.of("img.jpeg"), format);

        assertTrue(Files.exists(Path.of("img.jpeg")));
    }

    @AfterAll
    public static void deleteTemporaryDir() throws IOException {
        Files.deleteIfExists(Path.of("testDir"));
        Files.deleteIfExists(Path.of("img.png"));
        Files.deleteIfExists(Path.of("img.bmp"));
        Files.deleteIfExists(Path.of("img.jpeg"));
    }

    private FractalImage createTestImage() {
        Pixel[][] pixels = new Pixel[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                pixels[row][col] = new Pixel(row, col, 255, 127, 63); // Adjust RGB values as needed

            }
        }

        return new FractalImage(pixels, 3, 3);
    }
}
