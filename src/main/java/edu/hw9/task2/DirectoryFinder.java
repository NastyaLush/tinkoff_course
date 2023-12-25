package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DirectoryFinder {

    public List<Path> find(Path dir) throws IOException {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(new Finder(dir));
        }
    }

    static class Finder extends RecursiveTask<List<Path>> {

        private final Path path;
        private final List<Finder> finders = new ArrayList<>();
        private long counter = 0;
        private final long countOfDirectories = 1000;

        Finder(Path path) {
            this.path = path;
        }

        @Override
        protected List<Path> compute() {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
                for (Path next : directoryStream) {
                    if (Files.isDirectory(next)) {
                        finders.add(new Finder(next));
                    } else {
                        counter++;
                    }
                }
                List<Path> paths = new ArrayList<>(finders.parallelStream()
                                                          .flatMap((finder) -> {
                                                              finder.fork();
                                                              return finder.join()
                                                                           .stream();
                                                          })
                                                          .toList());

                if (counter > countOfDirectories) {
                    paths.add(path.toAbsolutePath());
                }

                return paths;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
