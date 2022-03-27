package task1;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    @Test
    public void shouldStart() {
        String expected = "Write action (buy, fill, take, remaining, exit): ";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        String userInput = String.format("buy%sback%sexit",
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        Main.main(null);

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];
        assertEquals(expected, actual);
    }
}
