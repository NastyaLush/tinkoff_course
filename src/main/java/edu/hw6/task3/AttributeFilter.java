package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AttributeFilter implements Filter {

    private final AttributeFilterOptions options;

    public AttributeFilter(AttributeFilterOptions options) {
        this.options = options;
    }

    @Override
    public boolean accept(Path entry) throws IOException {
        return switch (options) {
            case READABLE -> Files.isReadable(entry);
            case WRITABLE -> Files.isWritable(entry);
            case HIDDEN -> Files.isHidden(entry);
            case REGULAR_FILE -> Files.isRegularFile(entry);
            case DIRECTORY -> Files.isDirectory(entry);
            case EXECUTABLE -> Files.isExecutable(entry);
            case SYMBOLIC_LINK -> Files.isSymbolicLink(entry);
        };
    }
}
