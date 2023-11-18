package edu.project3.output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class FilePrinter implements Printer {

    private final String reportFileName = "reports/report";
    private final String reportDirName = "reports";
    private final CloneFile cloneFile = new CloneFile();
    private final Path path;

    public FilePrinter(OutputType outputType) throws IOException {
        Path dirPath = Path.of(reportDirName);

        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
        }

        this.path = cloneFile.cloneFile(reportFileName + "_" + LocalDate.now() + "." + outputType);
    }

    @Override
    public void print(String string) {
        try {
            Files.write(this.path, string.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            log.error("impossible to write to file " + path.getFileName());
        }
    }
}
