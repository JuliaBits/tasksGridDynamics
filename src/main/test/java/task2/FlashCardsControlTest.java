package task2;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlashCardsControlTest {
    @Test
    public void shouldStartWithRemoveAndNotRemove() {
        String userInput = String.format("add%sFrance%sParis%sremove%sFrance%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "The card has been removed.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        FlashCardsControl flashCardsControl = new FlashCardsControl();
        flashCardsControl.start();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 4];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldStartWithRemove() {
        String userInput = String.format("remove%sFrance%sexit",
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "Can't remove \"France\": there is no such card.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        FlashCardsControl flashCardsControl = new FlashCardsControl();
        flashCardsControl.start();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 4];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldStartWithAdd() {
        String userInput = String.format("add%sFrance%sParis%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "The pair (\"France\":\"Paris\") has been added.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        FlashCardsControl flashCardsControl = new FlashCardsControl();
        flashCardsControl.start();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 4];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotAddFlashcardBecauseOfTerm() throws IllegalAccessException {
        String userInput = String.format("add%sFrance%sParis%sadd%sFrance%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "The card \"France\" already exists.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 4];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotAddFlashcardBecauseOfDefinition() throws IllegalAccessException {
        String userInput = String.format("add%sCity%sParis%sadd%sFrance%sParis%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "The definition \"Paris\" already exists.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 4];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldPrintHardestCard() throws IllegalAccessException {
        String userInput = String.format("hardest card%sexit",
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "The hardest card is \"[Azerbaijan]\". You have 5 errors answering it.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 4];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotPrintHardestCard() {
        String userInput = String.format("hardest card%sexit",
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "There are no cards with errors.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        FlashCardsControl flashCardsControl = new FlashCardsControl();
        flashCardsControl.start();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 4];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldPrintHardestCards() throws IllegalAccessException {
        String userInput = String.format("hardest card%sexit",
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "The hardest cards are \"[Great Britain, Azerbaijan]\". You have 5 errors answering them.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccessForHardFlashcards();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 4];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSaveLog() {
        String userInput = String.format("add%sFrance%sParis%slog%sfile.txt%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "The log has been saved.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        FlashCardsControl flashCardsControl = new FlashCardsControl();
        flashCardsControl.start();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 4];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldResetStats() throws IllegalAccessException {
        String userInput = String.format("reset stats%sexit",
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "Card statistics have been reset.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 4];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotResetStats() {
        String userInput = String.format("reset stats%sexit",
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "You don`t have flashcards";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        FlashCardsControl flashCardsControl = new FlashCardsControl();
        flashCardsControl.start();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 4];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldAskWithSecondWrongAnswer() throws IllegalAccessException {
        String userInput = String.format("ask%s2%sLondon%sLondon%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "Wrong. The right answer is \"Baku\", but your definition is correct for \"Great Britain\".";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 4];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldAskWithWrongAnswer() throws IllegalAccessException {
        String userInput = String.format("ask%s1%sTokyo%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "Wrong. The right answer is \"London\".";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 4];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotAsk() {
        String userInput = String.format("ask%sexit",
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "Add cards, please.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        FlashCardsControl flashCardsControl = new FlashCardsControl();
        flashCardsControl.start();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 4];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldAsk() throws IllegalAccessException {
        String userInput = String.format("ask%s2%sLondon%sBaku%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "Correct!";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 4];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotImportWithArgs() {
        String userInput = "exit";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "File not found.\n" +
                "0 cards have been saved.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        String[] args = {"-import", "unknown.txt", "-export", "export.txt"};
        Main.main(args);

        String[] lines = baos.toString().split(System.lineSeparator());
        String first = lines[lines.length - 5];
        String second = lines[lines.length - 4];
        String actual = first + "\n" + second;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldExportWithArgs() {
        String userInput = "exit";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "6 cards have been loaded.\n" +
                "6 cards have been saved.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        String[] args = {"-export", "src/main/test/resources/export.txt", "-import", "src/main/test/resources/file.txt"};
        Main.main(args);

        String[] lines = baos.toString().split(System.lineSeparator());
        String first = lines[lines.length - 5];
        String second = lines[lines.length - 4];
        String actual = first + "\n" + second;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldImportWithArgs() {
        String userInput = "exit";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "6 cards have been loaded.\n" +
                "6 cards have been saved.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        String[] args = {"-import", "src/main/test/resources/file.txt", "-export", "src/main/test/resources/export.txt"};
        Main.main(args);

        String[] lines = baos.toString().split(System.lineSeparator());
        String first = lines[lines.length - 5];
        String second = lines[lines.length - 4];
        String actual = first + "\n" + second;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldExport() {
        String userInput = "exit";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "0 cards have been saved.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        String[] args = {"-export", "src/main/test/resources/export.txt"};
        Main.main(args);

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldImport() {
        String userInput = "exit";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "6 cards have been loaded.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        String[] args = {"-import", "src/main/test/resources/file.txt"};
        Main.main(args);

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldImportByUser() throws IllegalAccessException {
        String userInput = String.format("import%sfile.txt%simport%sfile.txt%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "3 cards have been saved.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];
        assertEquals(expected, actual);
    }

    private List<Flashcard> createFlashcards() {
        Flashcard flashcard1 = new Flashcard("Great Britain", "London");
        Flashcard flashcard2 = new Flashcard("Azerbaijan", "Baku");
        flashcard2.setWrongAnswers(5);
        List<Flashcard> flashcards = new ArrayList<>();
        flashcards.add(flashcard1);
        flashcards.add(flashcard2);
        return flashcards;
    }

    private List<Flashcard> createHardFlashcards() {
        Flashcard flashcard1 = new Flashcard("Great Britain", "London");
        Flashcard flashcard2 = new Flashcard("Azerbaijan", "Baku");
        flashcard1.setWrongAnswers(5);
        flashcard2.setWrongAnswers(5);
        List<Flashcard> flashcards = new ArrayList<>();
        flashcards.add(flashcard1);
        flashcards.add(flashcard2);
        return flashcards;
    }

    private void getAccess() throws IllegalAccessException {
        Field reader = null;
        try {
            reader = FlashCardsControl.class.getDeclaredField("flashcards");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert reader != null;
        reader.setAccessible(true);
        FlashCardsControl flashcards = new FlashCardsControl();
        reader.set(flashcards, createFlashcards());
        flashcards.start();
    }

    private void getAccessForHardFlashcards() throws IllegalAccessException {
        Field reader = null;
        try {
            reader = FlashCardsControl.class.getDeclaredField("flashcards");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert reader != null;
        reader.setAccessible(true);
        FlashCardsControl flashcards = new FlashCardsControl();
        reader.set(flashcards, createHardFlashcards());
        flashcards.start();
    }
}
