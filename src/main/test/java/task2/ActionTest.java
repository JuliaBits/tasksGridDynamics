package task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionTest {
    @Test
    public void shouldFindByOption() {
        assertEquals(Action.ADD, Action.findByOption("add"));
    }
    @Test
    public void shouldConvertToString() {
        String actions = "add, ask, remove, import, export, hardest card, reset stats, log, exit";
        assertEquals(actions, Action.actionsToString());
    }
}
