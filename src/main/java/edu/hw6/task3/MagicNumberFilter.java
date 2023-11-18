package edu.hw6.task3;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MagicNumberFilter implements Filter {

    private byte[] magicNumber;

    public MagicNumberFilter(byte... magicNumber) {
        this.magicNumber = magicNumber;
    }

    @Override
    public boolean accept(Path entry) throws IOException {
        if (!Files.isRegularFile(entry)) {
            throw new IllegalArgumentException();
        }
        try (BufferedInputStream stream = new BufferedInputStream(Files.newInputStream(entry))) {
            byte[] fileBytes = stream.readNBytes((int) Math.min(magicNumber.length, Files.size(entry)));
            if (fileBytes.length < magicNumber.length) {
                return false;
            }
            for (int i = 0; i < magicNumber.length; i++) {
                if (fileBytes[i] != magicNumber[i]) {
                    return false;
                }
            }
            return true;
        }

    }
}
