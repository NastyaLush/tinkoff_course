package edu.project3.argument;

import java.nio.file.Path;

@FunctionalInterface
public interface Predicate {

    boolean test(Path path);
}
