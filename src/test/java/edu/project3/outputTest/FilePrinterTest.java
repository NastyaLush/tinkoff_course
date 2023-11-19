package edu.project3.outputTest;

import edu.project3.output.CloneFile;
import edu.project3.output.FilePrinter;
import edu.project3.output.OutputType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class FilePrinterTest {

    //-Dnet.bytebuddy.experimental=true
    @Mock
    CloneFile cloneFile;

    @Test
    public void constructor_shouldCallCloneFIleMethod() throws IOException {
        FilePrinter filePrinter = new FilePrinter(cloneFile, OutputType.ADOC);
        Mockito.verify(cloneFile, Mockito.times(1))
               .cloneFile(Mockito.any());
    }
}
