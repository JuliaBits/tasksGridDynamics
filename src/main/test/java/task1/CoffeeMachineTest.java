package task1;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoffeeMachineTest {
    @Test
    public void shouldStartWithFill() {
        String userInput = String.format("fill%s100%s100%s100%s100%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.start();

        assertEquals(220, coffeeMachine.getCoffeeLeft());
        assertEquals(640, coffeeMachine.getMilkLeft());
        assertEquals(500, coffeeMachine.getWaterLeft());
    }

    @Test
    public void shouldStartWithTake() {
        String userInput = String.format("take%sexit",
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.start();

        assertEquals(0, coffeeMachine.getMoney());
    }

    @Test
    public void shouldStartWithBuy() {
        String userInput = String.format("buy%s1%sexit",
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.start();

        assertEquals(554, coffeeMachine.getMoney());
        assertEquals(100, coffeeMachine.getCoffeeLeft());
        assertEquals(540, coffeeMachine.getMilkLeft());
        assertEquals(150, coffeeMachine.getWaterLeft());
        assertEquals(8, coffeeMachine.getDisposableCups());
    }

    @Test
    public void shouldStartWithBuyButThenBack() {
        String userInput = String.format("buy%sback%sexit",
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "Write action (buy, fill, take, remaining, exit): ";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.start();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldStartWithBuyButNotEnoughResources() {
        String userInput = String.format("buy%s1%sexit",
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "Sorry, not enough coffee!\n" +
                "Sorry, not enough water!\n" +
                "Sorry, not enough cups!";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        CoffeeMachine coffeeMachine = new CoffeeMachine(0, 0, 0, 0, 0);
        coffeeMachine.start();

        String[] lines = baos.toString().split(System.lineSeparator());
        String first = lines[lines.length - 4];
        String second = lines[lines.length - 3];
        String third = lines[lines.length - 2];
        String actual = first + "\n" + second + "\n" + third;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldStartWithExit() {
        String userInput = "exit";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.start();
    }

    @Test
    public void shouldStartWithRemaining() {
        String userInput = String.format("remaining%sexit",
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        CoffeeMachine machine = new CoffeeMachine();

        String expected = "The coffee machine has: \n"
                + machine.getWaterLeft() + " ml of water\n"
                + machine.getMilkLeft() + " ml of milk\n"
                + machine.getCoffeeLeft() + " g of coffee beans\n"
                + machine.getDisposableCups() + " disposable cups\n"
                + machine.getMoney() + "$ of money";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.start();

        String[] lines = baos.toString().split(System.lineSeparator());
        String firstOutput = lines[lines.length - 3];
        String secondOutput = lines[lines.length - 2];
        String actual = firstOutput + "\n" + secondOutput;
        assertEquals(expected, actual);
    }
}
