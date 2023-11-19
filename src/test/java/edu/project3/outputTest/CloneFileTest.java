package edu.project3.outputTest;


import edu.project3.output.CloneFile;
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

    @BeforeAll
    private static void createDir() throws IOException {
        String dir = "d";
        if (!Files.exists(Path.of(dir))) {
            Files.createDirectory(Path.of(dir));
        }
    }

    @AfterAll
    private static void deleteFiles() throws IOException {
        ArrayList<String> filesTODelete = new ArrayList<>();
        filesTODelete.add("d/test file.sddf");
        filesTODelete.add("d/test file(1).sddf");
        filesTODelete.add("d/test file(2).sddf");
        filesTODelete.add("d/test file(3).sddf");
        filesTODelete.add("d");
        for (String file : filesTODelete) {
            if (Files.exists(Path.of(file))) {
                Files.delete(Path.of(file));
            }
        }
    }

    public static Stream<Arguments> cloneFileProvider() {
        return Stream.of(
                Arguments.of(
                        "d/test file.sddf", Path.of("d/test file.sddf")
                ),
                Arguments.of(
                        "d/test file.sddf", Path.of("d/test file(1).sddf")
                ), Arguments.of(
                        "d/test file.sddf", Path.of("d/test file(2).sddf")
                ), Arguments.of(
                        "d/test file.sddf", Path.of("d/test file(3).sddf")
                )
        );
    }

    @ParameterizedTest
    @MethodSource("cloneFileProvider")
    public void cloneFile_shouldCreateCopyFileIfExist(String givenPath, Path expectedPath) {

        CloneFile cloneFile = new CloneFile();
        Path actualPath;
        try {
            actualPath = cloneFile.cloneFile(givenPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(expectedPath, actualPath);
    }

    @Test
    @DisplayName("clone file should throw exception if file has no extension")
    public void cloneFile_shouldThrowExceptionInNoExtension() {
        assertThrows(IllegalArgumentException.class, () -> new CloneFile().cloneFile("hhh"));
    }


}
