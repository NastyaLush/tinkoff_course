package edu.hw6.task3;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class MagicNumberFilterTest {

    private final static Path pngPath = Path.of("test.png");
    private final static Path path = Path.of("test");
    private final static int width = 100;
    private final static int height = 100;

    @BeforeAll
    public static void createFiles() throws IOException {
        File img = pngPath.toFile();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        ImageIO.write(image, "png", img);
        Files.createFile(path);

    }

    public static Stream<Arguments> magicNumberProvider() {
        return Stream.of(
                Arguments.of(pngPath, true),
                Arguments.of(path, false)
        );
    }

    @ParameterizedTest(name = "path:{0} expected answer {1}")
    @MethodSource("magicNumberProvider")
    public void accept_shouldWorkCorrectly(Path path, boolean expectedAnswer) throws IOException {
        MagicNumberFilter magicNumberFilter = new MagicNumberFilter((byte) 0x89, (byte) 'P', (byte) 'N', (byte) 'G');

        Boolean actualAnswer = magicNumberFilter.accept(path);

        assertEquals(expectedAnswer, actualAnswer);

    }

    @AfterAll
    public static void deleteFiles() throws IOException {
        Files.deleteIfExists(path);
        Files.deleteIfExists(pngPath);
    }


}
