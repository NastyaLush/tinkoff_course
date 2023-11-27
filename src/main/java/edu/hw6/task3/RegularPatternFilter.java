package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class RegularPatternFilter implements Filter {

    private final Pattern pattern;

    public RegularPatternFilter(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean accept(Path entry) throws IOException {
        return pattern.matcher(entry.getFileName()
                                    .toString())
                      .matches();
    }
}
