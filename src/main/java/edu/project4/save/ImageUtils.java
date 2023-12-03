package edu.project4.save;

import edu.project4.structures.FractalImage;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ImageUtils {

    private ImageUtils() {}

    public static void save(FractalImage image, Path filename, ImageFormat format) throws IOException {
        BufferedImage bi = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_BGR);

        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                bi.setRGB(
                    x,
                    y,
                    new Color(
                        image.pixel(x, y).getR(),
                        image.pixel(x, y).getG(),
                        image.pixel(x, y).getB()
                    ).getRGB()
                );
            }
        }

        if (Files.exists(filename)) {
            Files.delete(filename);
            log.info("delete existing image");
        }
        File file = Files.createFile(filename).toFile();
        log.info("create image with path: " + file.getAbsolutePath());
        ImageIO.write(bi, format.getFormat(), file);

    }

}
