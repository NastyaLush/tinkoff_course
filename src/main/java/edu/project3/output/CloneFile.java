package edu.project3.output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CloneFile implements Cloner {

    public Path cloneFile(String pathName) throws IOException {
        Path path = Path.of(pathName);
        if (!path.getFileName()
                 .toString()
                 .contains(".")) {
            throw new IllegalArgumentException();
        }
        if (!Files.exists(path)) {
            Files.createFile(path);
            return path;
        }
        String[] splitedFilename = path.getFileName()
                                       .toString()
                                       .split("\\.");
        String filename = splitedFilename[0];
        String ext = splitedFilename[1];
        final Pattern pattern = Pattern.compile("^" + filename + "(\\(\\d*\\))?\\." + ext + "$");
        try (Stream<Path> siblings = Files.list(path.getParent())) {
            long count = siblings.filter(Files::isRegularFile)
                                 .filter((file) -> pattern.matcher(file.getFileName()
                                                                       .toString())
                                                          .matches())
                                 .count();
            Path newPath;
            newPath = Path.of(path.getParent() + "/" + filename + "(" + count + ")." + ext);

            Files.createFile(newPath);
            return newPath;
        }
    }

}
