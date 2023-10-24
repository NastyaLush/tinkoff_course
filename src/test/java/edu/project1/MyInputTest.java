package edu.project1;

import edu.project1.io.MyInput;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class MyInputTest {

    private final String expectedString = "smth";
    private final InputStream sysInBackup = System.in;
    private final ByteArrayInputStream in = new ByteArrayInputStream(expectedString.getBytes());

    @BeforeEach
    public void setUp() {
        System.setIn(in);
    }

    @Test
    public void methodRead_shouldRead() {
        MyInput input = new MyInput();

        String actualString = input.read();

        assertEquals(actualString, expectedString);
    }

    @AfterEach
    public void tearDown() {
        System.setIn(sysInBackup);
    }

}
