package edu.project3.output;

import java.io.IOException;
import java.nio.file.Path;

public interface Cloner {

    Path cloneFile(String filename) throws IOException;

}
