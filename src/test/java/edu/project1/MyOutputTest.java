package edu.project1;

import edu.project1.io.MyOutput;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MyOutputTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void methodWrite_shouldWrite() {
        MyOutput myOutput = new MyOutput();
        String expectedString = "kdnv;jdenv;jdw";

        myOutput.write(expectedString);
        String actualString = outputStreamCaptor.toString().trim();

        assertEquals(expectedString, actualString);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
