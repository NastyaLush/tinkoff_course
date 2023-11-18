package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SizeFilter implements Filter {

    private final Long size;
    private final SizeFilterOptions options;

    public SizeFilter(Long size, SizeFilterOptions options) {
        this.size = size;
        this.options = options;
    }

    @Override
    public boolean accept(Path entry) throws IOException {
        switch (options) {
            case LARGER -> {
                return Files.size(entry) > size;
            }
            case SMALLER -> {
                return Files.size(entry) < size;
            }
            case EQUAL -> {
                return Files.size(entry) == size;
            }
            default -> throw new IllegalArgumentException();
        }

    }

}
