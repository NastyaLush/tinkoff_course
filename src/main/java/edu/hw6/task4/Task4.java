package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class Task4 {

    public void complete(Path path) throws IOException {
        if (Files.exists(path)) {
            throw new IllegalArgumentException();
        }
        Files.createFile(path);
        try (PrintWriter printWriter =
                     new PrintWriter(
                             new OutputStreamWriter(
                                     new BufferedOutputStream(
                                             new CheckedOutputStream(Files.newOutputStream(path), new Adler32()))))) {

            printWriter.write("Programming is learned by writing programs. ― Brian Kernighan");
        }
    }

}
