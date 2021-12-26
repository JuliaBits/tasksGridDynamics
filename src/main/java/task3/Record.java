package task3;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class Record {
    private String number;
    private LocalDateTime created;
    private LocalDateTime lastEdit;
    protected final List<Record> records = new ArrayList<>();
    protected final Scanner sc = new Scanner(System.in);

    private boolean numberValidity(String number) {
        String numberPattern = "^((\\+?\\(?[^\\W_]+\\)?)|(\\+?\\(?[^\\W_]+\\)?(-|\\s)?[^\\W_]{2,})|(\\+?[^\\W_]+(-|\\s)?\\(?[^\\W_]{2,}\\)?))((-|\\s)?[^\\W_]{2,})*";
        Pattern pattern = Pattern.compile(numberPattern);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    public void start() {
        while (true) {
            System.out.println("\nEnter action (add, list, search, count, exit):");
            String input = sc.nextLine();
            if (input.equals("add")) {
                add();
            }
            if (input.equals("list")) {
                list();
            }
            if (input.equals("search")) {
                search();
            }
            if (input.equals("count")) {
                count();
            }
            if (input.equals("exit")) {
                break;
            }
        }
    }

    public void search() {
        System.out.println("Enter search query:");
        String searchedQuery = sc.nextLine();
        Pattern pattern = Pattern.compile("(?i).*" + searchedQuery + ".*");
        List<Record> foundRecords = records.stream()
                .filter(record -> pattern.matcher(record.toString()).find() || pattern.matcher(record.getNumber()).find())
                .collect(Collectors.toList());
        System.out.println("Found " + foundRecords.size() + " results:");
        if (!foundRecords.isEmpty()) {
            printRecords(foundRecords);
            actionsWithRecord(foundRecords);
        }
    }

    public void actionsWithRecord(List<Record> foundRecords) {
        System.out.println("\nEnter action (number of record, back, again):");
        String input = sc.nextLine();
        Pattern pattern = Pattern.compile("\\s*[0-" + foundRecords.size() + "]\\s*");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            int indexOfRecord = Integer.parseInt(input);
            info(foundRecords.get(indexOfRecord - 1));
            actionsEditDeleteMenu(foundRecords.get(indexOfRecord - 1));
        } else if (input.equals("again")) {
            search();
        } else if (input.equals("back")) {
            start();
        }
    }

    public void actionsEditDeleteMenu(Record record) {
        System.out.println("\nEnter action (edit, delete, menu):");
        String input = sc.nextLine();
        switch (input) {
            case "edit":
                editRecord(record);
                break;
            case "delete":
                remove();
                break;
            case "menu":
                break;
            default:
                actionsEditDeleteMenu(record);
        }
    }

    public void info(Record recordInfo) {
        if (recordInfo.getClass() == Person.class) {
            System.out.println(((Person) recordInfo).personInfo());
        } else if ((recordInfo.getClass() == Organization.class)) {
            System.out.println(((Organization) recordInfo).organizationInfo());
        }
    }

    public void add() {
        System.out.println("\nEnter the type (person, organization):");
        String typeOfRecord = sc.nextLine();
        if (typeOfRecord.equalsIgnoreCase("person")) {
            Person newPerson = new Person();
            Record newRecord = newPerson.createRecord();
            records.add(newRecord);
            System.out.println("The record added.");
        } else if (typeOfRecord.equalsIgnoreCase("organization")) {
            Organization organization = new Organization();
            Record newRecord = organization.createRecord();
            records.add(newRecord);
            System.out.println("The record added.");
        }
    }

    public abstract Record createRecord();

    public void remove() {
        if (records.isEmpty()) {
            System.out.println("No records to remove!");
        } else {
            Record recordToRemove = selectRecord();
            if (recordToRemove != null) {
                records.remove(recordToRemove);
                System.out.println("Successfully deleted!");
            }
        }
    }

    public Record selectRecord() {
        while (true) {
            System.out.println("Enter action (number of record, back):");
            String input = sc.nextLine();
            if (input.equals("back")) {
                break;
            } else {
                if (isValidIndex(input)) {
                    int indexOfRecord = Integer.parseInt(input) - 1;
                    return records.get(indexOfRecord);
                }
            }
        }
        return null;
    }

    public void editRecord(Record record) {
        if (records.isEmpty()) {
            System.out.println("No records to edit!");
        } else {
            if (record.getClass() == Organization.class) {
                Organization toUpdate = (Organization) record;
                toUpdate.edit(record);
                info(record);
            } else if (record.getClass() == Person.class) {
                Person toUpdate = (Person) record;
                toUpdate.edit(record);
                info(record);
            }
        }
        actionsEditDeleteMenu(record);
    }

    public void count() {
        System.out.println("The Phone Book has " + records.size() + " records.");
    }

    public void list() {
        if (!records.isEmpty()) {
            printRecords(records);
            actionsWithRecord();
        } else {
            System.out.println("The Phone Book is empty. Please, add new contact.");
        }
    }

    public void actionsWithRecord() {
        Record current = selectRecord();
        info(current);
        actionsEditDeleteMenu(current);
    }

    public void printRecords(List<Record> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1 + ". " + list.get(i).toString()));
        }
    }

    public boolean isValidIndex(String input) {
        try {
            Integer.parseInt(input);
        } catch (Exception e) {
            return false;
        }
        return Integer.parseInt(input) <= records.size();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (numberValidity(number)) {
            this.number = number;
        } else {
            System.out.println("Wrong number format!");
            this.number = "[no data]";
        }
    }

    public void setNumber() {
        System.out.println("Enter number:");
        this.setNumber(sc.nextLine());
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(LocalDateTime lastEdit) {
        this.lastEdit = created;
    }

    public abstract String toString();
}
