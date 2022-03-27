package task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlashcardTest {
    private final Flashcard flashcard = new Flashcard("France", "Paris");

    @Test
    public void shouldGetTerm() {
        assertEquals("France", flashcard.getTerm());
    }

    @Test
    public void shouldGetDefinition() {
        assertEquals("Paris", flashcard.getDefinition());
    }

    @Test
    public void shouldGetWrongAnswers() {
        assertEquals(0, flashcard.getWrongAnswers());
    }

    @Test
    public void shouldAddWrongAnswer() {
        flashcard.addWrongAnswer();
        assertEquals(1, flashcard.getWrongAnswers());
    }

    @Test
    public void shouldSetWrongAnswers() {
        flashcard.setWrongAnswers(5);
        assertEquals(5, flashcard.getWrongAnswers());
    }
}
