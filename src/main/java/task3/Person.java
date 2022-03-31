package task3;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

public class Person extends Record {
    private String name;
    private String surname;
    private String birthDate;
    private String gender;
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    private enum Gender {
        MALE("M"),
        FEMALE("F"),
        UNKNOWN("U");

        private final String genderValue;

        Gender(String gender) {
            this.genderValue = gender;
        }

        private static Gender findByGender(String genderValue) {
            return Arrays.stream(Gender.values())
                    .filter(s -> s.getGenderValue().equals(genderValue))
                    .findAny()
                    .orElse(UNKNOWN);
        }

        public String getGenderValue() {
            return genderValue;
        }
    }

    public Person createRecord() {
        Person newPerson = new Person();
        newPerson.setName();
        newPerson.setSurname();
        newPerson.setBirthDate();
        newPerson.setGender();
        newPerson.setNumber();
        newPerson.setCreated(LocalDateTime.now().withNano(0));
        newPerson.setLastEdit(LocalDateTime.now().withNano(0));
        return newPerson;
    }

    public String personInfo() {
        return "Name: " + name + "\n" +
                "Surname: " + surname + "\n" +
                "Birth date: " + birthDate + "\n" +
                "Gender: " + gender + "\n" +
                "Number: " + super.getNumber() + "\n" +
                "Time created: " + super.getCreated() + "\n" +
                "Time last edit: " + super.getLastEdit();
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    public void edit(Record record) {
        Person old = (Person) record;
        System.out.println("Select a field (name, surname, birth, gender, number):");
        String fieldToUpdate = sc.nextLine();
        switch (fieldToUpdate) {
            case "name": {
                old.setName();
                break;
            }
            case "surname": {
                old.setSurname();
                break;
            }
            case "number": {
                old.setNumber();
                break;
            }
            case "birth": {
                old.setBirthDate();
                break;
            }
            case "gender": {
                old.setGender();
                break;
            }
        }
        old.setLastEdit(LocalDateTime.now().withNano(0));
        System.out.println("Saved!");
    }

    private boolean isValidDate(String date) {
        Date today = new Date();
        try {
            DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            dateFormat.setLenient(false);
            dateFormat.parse(date);
            Date birthDate = dateFormat.parse(date);
            return birthDate.before(today);
        } catch (ParseException e) {
            return false;
        }
    }


    public void setName() {
        System.out.println("Enter name:");
        this.setName(sc.nextLine());
    }

    public void setSurname() {
        System.out.println("Enter surname:");
        this.setSurname(sc.nextLine());
    }

    public void setBirthDate(String birthDate) {
        if (isValidDate(birthDate)) {
            this.birthDate = birthDate;
        } else {
            System.out.println("Bad birth date!");
            this.birthDate = "[no data]";
        }
    }

    public void setBirthDate() {
        System.out.println("Enter birth date:");
        this.setBirthDate(sc.nextLine());
    }

    public void setGender(String gender) {
        Gender foundGender = Gender.findByGender(gender);
        if (!foundGender.equals(Gender.UNKNOWN)) {
            this.gender = gender;
        } else {
            System.out.println("Bad gender!");
            this.gender = "[no data]";
        }
    }

    public void setGender() {
        System.out.println("Enter gender (M, F):");
        this.setGender(sc.nextLine());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
