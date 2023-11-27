package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.Path;

public class ExtensionFilter implements Filter {

    private final String extension;

    public ExtensionFilter(String extension) {
        this.extension = extension;
    }


    @Override
    public boolean accept(Path entry) throws IOException {
        return entry.getFileName()
                    .toString()
                    .endsWith(extension);
    }
}
