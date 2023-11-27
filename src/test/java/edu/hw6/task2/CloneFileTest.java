package edu.hw6.task2;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

public class CloneFileTest {

    @AfterAll
    @BeforeAll
    private static void deleteFiles() throws IOException {
        ArrayList<String> filesTODelete = new ArrayList<>();
        filesTODelete.add("test file.sddf");
        filesTODelete.add("test file - копия.sddf");
        filesTODelete.add("test file - копия (2).sddf");
        filesTODelete.add("test file - копия (3).sddf");
        for (String file : filesTODelete) {
            if (Files.exists(Path.of(file))) {
                Files.delete(Path.of(file));
            }
        }
    }

    public static Stream<Arguments> cloneFileProvider() {
        return Stream.of(
                Arguments.of(
                        Path.of("test file.sddf"), Path.of("test file.sddf")
                ),
                Arguments.of(
                        Path.of("test file.sddf"), Path.of("test file - копия.sddf")
                ), Arguments.of(
                        Path.of("test file.sddf"), Path.of("test file - копия (2).sddf")
                ), Arguments.of(
                        Path.of("test file.sddf"), Path.of("test file - копия (3).sddf")
                )
        );
    }

    @ParameterizedTest
    @MethodSource("cloneFileProvider")
    public void cloneFile_shouldCreateCopyFileIfExist(Path givenPath, Path expectedPath) {

        Long expectedAnswer = 1L;
        Long actualAnswer;

        CloneFile cloneFile = new CloneFile();
        try {
            cloneFile.cloneFile(givenPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (Stream<Path> stream = Files.list(Path.of("."))) {
            actualAnswer = stream.filter(Files::isRegularFile)
                                 .filter((file) -> {
                                     try {
                                         return Files.isSameFile(file, expectedPath);
                                     } catch (IOException e) {
                                         throw new RuntimeException(e);
                                     }
                                 })
                                 .count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    @DisplayName("clone file should throw exception if file has no extension")
    public void cloneFile_shouldThrowExceptionInNoExtension() {
        assertThrows(IllegalArgumentException.class, () -> new CloneFile().cloneFile(Path.of("hhh")));
    }


}
