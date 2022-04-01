package task3;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecordTest {
    @Test
    public void shouldAddPerson() throws IllegalAccessException {
        String userInput = String.format("add%sperson%sOleg%sVinnik%s01-01-1990%sM%s123%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "The record added.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldAddPersonWithWrongBirthDateAndPhoneNumber() throws IllegalAccessException {
        String userInput = String.format("add%sperson%sOleg%sVinnik%s21-21-1990%sG%s(123)--(3)%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "The record added.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldAddOrganization() throws IllegalAccessException {
        String userInput = String.format("add%sorganization%sPizza%sPushkinska%s123%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "The record added.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldShowAllRecordsWhileBookIsEmpty() throws IllegalAccessException {
        String userInput = String.format("list%sexit",
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "The Phone Book is empty. Please, add new contact.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccessWithoutRecords();

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
    public void shouldCallRecursionMenu() throws IllegalAccessException {
        String userInput = String.format("list%s1%sf%sf%smenu%sexit",
                System.lineSeparator(),
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

    @Test
    public void shouldNotSelectRecordBecauseOfInvalidIndex() throws IllegalAccessException {
        String userInput = String.format("list%s4%sback%sexit",
                System.lineSeparator(),
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
    public void shouldSearchAgain() throws IllegalAccessException {
        String userInput = String.format("search%s12%sagain%s12%sback%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
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

        String expected = "The Phone Book has 2 records.";
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

        String expected = "The Phone Book has 2 records.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEditPerson() throws IllegalAccessException {
        String userInput = String.format("search%s12%s1%sedit%snumber%s134%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "Name: Viktor\n" +
                "Surname: Pushkin\n" +
                "Birth date: 01-01-2000\n" +
                "Gender: M\n" +
                "Number: 134\n" +
                "Time created: " + LocalDateTime.now().withNano(0) + "\n" +
                "Time last edit: " + LocalDateTime.now().withNano(0);
        ;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEditPersonSurname() throws IllegalAccessException {
        String userInput = String.format("list%s1%sedit%ssurname%sIvanov%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "Name: Viktor\n" +
                "Surname: Ivanov\n" +
                "Birth date: 01-01-2000\n" +
                "Gender: M\n" +
                "Number: 123\n" +
                "Time created: " + LocalDateTime.now().withNano(0) + "\n" +
                "Time last edit: " + LocalDateTime.now().withNano(0);
        ;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEditPersonName() throws IllegalAccessException {
        String userInput = String.format("list%s1%sedit%sname%sIvan%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "Name: Ivan\n" +
                "Surname: Pushkin\n" +
                "Birth date: 01-01-2000\n" +
                "Gender: M\n" +
                "Number: 123\n" +
                "Time created: " + LocalDateTime.now().withNano(0) + "\n" +
                "Time last edit: " + LocalDateTime.now().withNano(0);
        ;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEditPersonBirthDate() throws IllegalAccessException {
        String userInput = String.format("list%s1%sedit%sbirth%s11-11-2000%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "Name: Viktor\n" +
                "Surname: Pushkin\n" +
                "Birth date: 11-11-2000\n" +
                "Gender: M\n" +
                "Number: 123\n" +
                "Time created: " + LocalDateTime.now().withNano(0) + "\n" +
                "Time last edit: " + LocalDateTime.now().withNano(0);
        ;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEditPersonGender() throws IllegalAccessException {
        String userInput = String.format("list%s1%sedit%sgender%sK%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "Name: Viktor\n" +
                "Surname: Pushkin\n" +
                "Birth date: 01-01-2000\n" +
                "Gender: [no data]\n" +
                "Number: 123\n" +
                "Time created: " + LocalDateTime.now().withNano(0) + "\n" +
                "Time last edit: " + LocalDateTime.now().withNano(0);
        ;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEditOrganization() throws IllegalAccessException {
        String userInput = String.format("search%s456%s1%sedit%snumber%s578%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "Organization name: Pizza\n" +
                "Address: Sumska str\n" +
                "Number: 578\n" +
                "Time created: null\n" +
                "Time last edit: null";
        ;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEditOrganizationName() throws IllegalAccessException {
        String userInput = String.format("list%s2%sedit%sname%sNew Name%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "Organization name: New Name\n" +
                "Address: Sumska str\n" +
                "Number: 456\n" +
                "Time created: null\n" +
                "Time last edit: null";
        ;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEditOrganizationAddress() throws IllegalAccessException {
        String userInput = String.format("list%s2%sedit%saddress%sNew str%sexit",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "Organization name: Pizza\n" +
                "Address: New str\n" +
                "Number: 456\n" +
                "Time created: null\n" +
                "Time last edit: null";
        ;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        getAccess();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];
        assertEquals(expected, actual);
    }

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
        record.setGender("M");
        record.setBirthDate("01-01-2000");
        record.setNumber("123");
        record.setName("Viktor");
        record.setSurname("Pushkin");
        record.setCreated(LocalDateTime.now().withNano(0));
        Organization org = new Organization();
        org.setOrgName("Pizza");
        org.setAddress("Sumska str");
        org.setNumber("456");
        recordList.add(record);
        recordList.add(org);
        return recordList;
    }

    private void getAccess() throws IllegalAccessException {
        Field reader = null;
        Field reader2 = null;
        try {
            reader = Record.class.getDeclaredField("sc");
            reader2 = Record.class.getDeclaredField("records");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert reader != null;
        assert reader2 != null;
        reader.setAccessible(true);
        reader2.setAccessible(true);
        Record record = new Record();
        reader.set(record, new Scanner(System.in));
        reader2.set(record, createRecords());
        record.start();
    }

    private void getAccessWithoutRecords() throws IllegalAccessException {
        Field reader = null;
        try {
            reader = Record.class.getDeclaredField("sc");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert reader != null;
        reader.setAccessible(true);
        Record record = new Record();
        reader.set(record, new Scanner(System.in));
        record.start();
    }
}
