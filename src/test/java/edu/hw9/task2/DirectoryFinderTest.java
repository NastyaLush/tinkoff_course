package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectoryFinderTest {

    private static final String DIRECTORY_FINDER_TEST = "directoryFinderTest";

    @BeforeAll
    public static void generateFiles() throws IOException {

        Files.createDirectory(Path.of(DIRECTORY_FINDER_TEST));
        for (int i = 0; i < 999; i++) {
            Files.createFile(Path.of(DIRECTORY_FINDER_TEST + "/" + i));
        }
        Files.createDirectory(Path.of(DIRECTORY_FINDER_TEST + "/dir"));
        for (int i = 0; i < 1050; i++) {
            Files.createFile(Path.of(DIRECTORY_FINDER_TEST + "/dir/" + i));
        }
    }

    @AfterAll
    public static void deleteFiles() throws IOException {
        for (int i = 0; i < 1050; i++) {
            Files.deleteIfExists(Path.of(DIRECTORY_FINDER_TEST + "/" + i));
        }
        for (int i = 0; i < 1050; i++) {
            Files.deleteIfExists(Path.of(DIRECTORY_FINDER_TEST + "/dir/" + i));
        }
        Files.deleteIfExists(Path.of(DIRECTORY_FINDER_TEST + "/dir"));
        Files.deleteIfExists(Path.of(DIRECTORY_FINDER_TEST));
    }

    @Test
    public void find_shouldFindDirectoriesThatHaveMoreThan1000Files() throws IOException {
        DirectoryFinder directoryFinder = new DirectoryFinder();
        List<Path> expectedAnswer = List.of(Path.of(DIRECTORY_FINDER_TEST + "/dir").toAbsolutePath());
        List<Path> actualAnswer = directoryFinder.find(Path.of(DIRECTORY_FINDER_TEST));

        assertEquals(expectedAnswer, actualAnswer);
    }
}
