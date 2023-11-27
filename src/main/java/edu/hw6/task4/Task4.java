package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class Task4 {

    @SuppressWarnings("NestedTryDepth")
    public void complete(Path path) throws IOException {
        if (Files.exists(path)) {
            throw new IllegalArgumentException();
        }
        Files.createFile(path);
        try (OutputStream outputStream = Files.newOutputStream(path)) {
            try (CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new Adler32())) {
                try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream)) {
                    try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream)) {
                        try (PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {
                            printWriter.write("Programming is learned by writing programs. ― Brian Kernighan");
                        }
                    }
                }
            }
        }
    }

}
