package edu.project3.argumentWork;

import java.nio.file.Path;

@FunctionalInterface
public interface PredicatePath {

    boolean test(Path path);
}
