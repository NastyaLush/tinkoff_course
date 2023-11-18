package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CloneFile {

    public void cloneFile(Path path) throws IOException {
        if (!path.getFileName()
                 .toString()
                 .contains(".")) {
            throw new IllegalArgumentException();
        }
        if (!Files.exists(path)) {
            Files.createFile(path);
            return;
        }
        String[] splitedFilename = path.getFileName()
                                       .toString()
                                       .split("\\.");
        String filename = splitedFilename[0];
        String ext = splitedFilename[1];
        final Pattern pattern = Pattern.compile("^" + filename + "( - копия( \\(\\d\\))?)?\\." + ext + "$");
        try (Stream<Path> siblings = Files.list(path.toAbsolutePath()
                                                    .getParent())) {
            long count = siblings.filter(Files::isRegularFile)

                                 .filter((file) -> pattern.matcher(file.getFileName()
                                                                       .toString())
                                                          .matches())
                                 .count();
            if (count == 1) {
                Files.createFile(Path.of(filename + " - копия." + ext));
            } else {
                Files.createFile(Path.of(filename + " - копия (" + count + ")." + ext));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
