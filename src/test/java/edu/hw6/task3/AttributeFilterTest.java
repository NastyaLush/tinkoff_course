package edu.hw6.task3;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Stream;

public class AttributeFilterTest {

    private final Path path = Path.of("test");
    private final Path hiddenPath = Path.of(".test");
    private final Path targetPath = Path.of("mainTest");

    public static Stream<Arguments> acceptProvider() {
        return Stream.of(
                Arguments.of(AttributeFilterOptions.DIRECTORY, AttributeFilterOptions.DIRECTORY, true),
                Arguments.of(AttributeFilterOptions.READABLE, AttributeFilterOptions.READABLE, true),
                Arguments.of(AttributeFilterOptions.HIDDEN, AttributeFilterOptions.HIDDEN, true),
                Arguments.of(AttributeFilterOptions.EXECUTABLE, AttributeFilterOptions.EXECUTABLE, true),
                Arguments.of(AttributeFilterOptions.REGULAR_FILE, AttributeFilterOptions.REGULAR_FILE, true),
                Arguments.of(AttributeFilterOptions.WRITABLE, AttributeFilterOptions.WRITABLE, true),
                Arguments.of(AttributeFilterOptions.SYMBOLIC_LINK, AttributeFilterOptions.SYMBOLIC_LINK, true),
                Arguments.of(AttributeFilterOptions.WRITABLE, AttributeFilterOptions.DIRECTORY, false),
                Arguments.of(AttributeFilterOptions.WRITABLE, AttributeFilterOptions.READABLE, false),
                Arguments.of(AttributeFilterOptions.WRITABLE, AttributeFilterOptions.HIDDEN, false),
                Arguments.of(AttributeFilterOptions.WRITABLE, AttributeFilterOptions.EXECUTABLE, false),
                Arguments.of(AttributeFilterOptions.DIRECTORY, AttributeFilterOptions.REGULAR_FILE, false),
                Arguments.of(AttributeFilterOptions.READABLE, AttributeFilterOptions.WRITABLE, false),
                Arguments.of(AttributeFilterOptions.WRITABLE, AttributeFilterOptions.SYMBOLIC_LINK, false)
        );
    }

    @AfterEach
    public void deleteTestFiles() throws IOException {

        Files.deleteIfExists(path);
        Files.deleteIfExists(hiddenPath);
        Files.deleteIfExists(targetPath);
    }

    @ParameterizedTest(name = "Crate As {0}, check {1} expected answer {2}")
    @MethodSource("acceptProvider")
    public void accept_shouldWorkCorrectly(AttributeFilterOptions optionForCreate, AttributeFilterOptions optionForCheck, boolean expectedResult) throws IOException {
        Path pathOfCreatedFile = createFileWithOption(optionForCreate);
        AttributeFilter attributeFilter = new AttributeFilter(optionForCheck);

        boolean actualResult = attributeFilter.accept(pathOfCreatedFile);

        assertEquals(expectedResult, actualResult);

    }

    private Path createFileWithOption(AttributeFilterOptions options) throws IOException {
        switch (options) {

            case READABLE -> {
                Files.createFile(path);
                Set<PosixFilePermission> permissions = EnumSet.of(PosixFilePermission.OWNER_READ, PosixFilePermission.GROUP_READ, PosixFilePermission.OTHERS_READ);
                Files.setPosixFilePermissions(path, permissions);
                return path;
            }
            case WRITABLE -> {
                Files.createFile(path);
                Set<PosixFilePermission> permissions = EnumSet.of(PosixFilePermission.OWNER_WRITE, PosixFilePermission.GROUP_WRITE, PosixFilePermission.OTHERS_WRITE);
                Files.setPosixFilePermissions(path, permissions);
                return path;
            }
            case REGULAR_FILE -> {
                Files.createFile(path);
                return path;
            }

            case EXECUTABLE -> {
                Files.createFile(path);
                Set<PosixFilePermission> permissions = EnumSet.of(PosixFilePermission.OWNER_EXECUTE, PosixFilePermission.GROUP_EXECUTE, PosixFilePermission.OTHERS_EXECUTE);
                Files.setPosixFilePermissions(path, permissions);
                return path;
            }
            case HIDDEN -> {
                Files.createFile(hiddenPath);
                return hiddenPath;
            }
            case DIRECTORY -> {
                Files.createDirectories(path);
                return path;
            }
            case SYMBOLIC_LINK -> {
                Files.createDirectory(targetPath);
                Files.createSymbolicLink(path, targetPath);
                return path;
            }
            default -> {
                return null;
            }
        }
    }

}
