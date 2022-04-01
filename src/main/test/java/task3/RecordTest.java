package task3;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecordTest {
//    @Test
//    public void shouldAddPerson() {
//        String userInput = String.format("add%sperson%sOleg%sVinnik%s01-01-1990%sM%s123%sexit",
//                System.lineSeparator(),
//                System.lineSeparator(),
//                System.lineSeparator(),
//                System.lineSeparator(),
//                System.lineSeparator(),
//                System.lineSeparator(),
//                System.lineSeparator());
//        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
//        System.setIn(bais);
//
//        String expected = "\nEnter action (add, list, search, count, exit):";
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        PrintStream printStream = new PrintStream(baos);
//        System.setOut(printStream);
//
//        Main.main(null);
//
//        String[] lines = baos.toString().split(System.lineSeparator());
//        String actual = lines[lines.length - 5];
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void shouldAddOrganization() {
//        String userInput = String.format("add%sorganization%sPizza%sPushkinska%s123%sexit",
//                System.lineSeparator(),
//                System.lineSeparator(),
//                System.lineSeparator(),
//                System.lineSeparator(),
//                System.lineSeparator());
//        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
//        System.setIn(bais);
//
//        String expected = "\nEnter action (add, list, search, count, exit):";
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        PrintStream printStream = new PrintStream(baos);
//        System.setOut(printStream);
//
//        Main.main(null);
//
//        String[] lines = baos.toString().split(System.lineSeparator());
//        String actual = lines[lines.length - 5];
//        assertEquals(expected, actual);
//    }

    @Test
    public void shouldShowAllRecordsWhileBookIsEmpty() {
        String userInput = String.format("list%sexit",
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "The Phone Book is empty. Please, add new contact.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        Main.main(null);

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldPrintRecords() throws IllegalAccessException {
        String userInput = String.format("list%sback%sexit",
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "Enter action (number of record, back):";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFind() throws IllegalAccessException {
        String userInput = String.format("search%s12%sback%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "\nEnter action (number of record, back, again):";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCount() throws IllegalAccessException {
        String userInput = String.format("count%sexit",
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "The Phone Book has 1 records.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotEditRecord() throws IllegalAccessException {
        String userInput = String.format("count%sexit",
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "The Phone Book has 1 records.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];
        assertEquals(expected, actual);
    }

//    @Test
//    public void shouldEdit() throws IllegalAccessException {
//        String userInput = String.format("search%s12%s1%sedit%snumber%s134%sexit",
//                System.lineSeparator(),
//                System.lineSeparator(),
//                System.lineSeparator(),
//                System.lineSeparator(),
//                System.lineSeparator(),
//                System.lineSeparator());
//        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
//        System.setIn(bais);
//
//        String expected = "The Phone Book has 1 records.";
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        PrintStream printStream = new PrintStream(baos);
//        System.setOut(printStream);
//
//        getAccess();
//
//        String[] lines = baos.toString().split(System.lineSeparator());
//        String actual = lines[lines.length - 1];
//        assertEquals(expected, actual);
//    }

    @Test
    public void shouldDelete() throws IllegalAccessException {
        String userInput = String.format("search%s12%s1%sdelete%s1%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "Successfully deleted!";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGoBackToTheMenu() throws IllegalAccessException {
        String userInput = String.format("search%s12%s1%smenu%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "\nEnter action (edit, delete, menu):";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];
        assertEquals(expected, actual);
    }

    private List<Record> createRecords() {
        List<Record> recordList = new ArrayList<>();
        Person record = new Person();
        record.setNumber("123");
        recordList.add(record);
        return recordList;
    }

    private void getAccess() throws IllegalAccessException {
        Field reader = null;
        try {
            reader = Record.class.getDeclaredField("records");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert reader != null;
        reader.setAccessible(true);
        Record records = new Record();
        reader.set(records, createRecords());
        records.start();
    }
}
