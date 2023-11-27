package edu.hw6.task3;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FilterTest {

    private static final Path dir = Path.of("testDir");
    private static final Path png = Path.of("testDir/image.png");
    private static final Path file = Path.of("testDir/testFile");

    @BeforeAll
    public static void createDirAndFiles() throws IOException {
        Files.createDirectory(dir);
        Files.createFile(file);
        File img = png.toFile();
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        ImageIO.write(image, "png", img);
    }

    @Test
    public void andShouldCreateChainOfFiltersAndFilterCorrectly() throws IOException {
        Integer expectedSize = 1;
        List<Path> paths = new ArrayList<>();
        DirectoryStream.Filter<Path> filter = new AttributeFilter(AttributeFilterOptions.READABLE)
                .and(new ExtensionFilter(".png"))
                .and(new MagicNumberFilter((byte) 0x89, (byte) 'P', (byte) 'N', (byte) 'G'))
                .and(new RegularPatternFilter(Pattern.compile("i\\w*.png")))
                .and(new SizeFilter(0L, SizeFilterOptions.LARGER));
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(paths::add);
            assertEquals(expectedSize, paths.size());
            assertEquals(png, paths.get(0));
        }
    }

    @AfterAll
    public static void deleteDirAndFiles() throws IOException {
        Files.deleteIfExists(png);
        Files.deleteIfExists(file);
        Files.deleteIfExists(dir);
    }

}
