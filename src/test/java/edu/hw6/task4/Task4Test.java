package edu.hw6.task4;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Task4Test {

    private static final Path givenPath = Path.of("test");

    @AfterAll
    private static void deleteCreatedFiles() throws IOException {
        if (Files.exists(givenPath)) {
            Files.delete(givenPath);
        }
    }

    @Test
    @DisplayName("should print text in file after all wrappers")
    public void complete_shouldPrintTextInFileAfterAllWrappers() throws IOException {
        if (Files.exists(givenPath)) {
            Files.delete(givenPath);
        }
        String expectedString = "Programming is learned by writing programs. ― Brian Kernighan";
        Integer expectedSize = 1;
        new Task4().complete(givenPath);

        List<String> actualStringList = Files.readAllLines(givenPath);
        Integer actualSize = actualStringList.size();
        String actualString = actualStringList.get(0);

        assertEquals(expectedSize, actualSize);
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName(" should throw exception if file exist")
    public void complete_shouldThrowExceptionIfFileExist() throws IOException {
        if (!Files.exists(givenPath)) {
            Files.createFile(givenPath);
        }
        assertThrows(IllegalArgumentException.class, () -> {
            new Task4().complete(givenPath);
        });
    }

}
