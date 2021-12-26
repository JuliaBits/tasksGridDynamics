package task3;

import java.time.LocalDateTime;

public class Person extends Record {
    private String name;
    private String surname;
    private String birthDate;
    private String gender;

    @Override
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

    public void saveUpdatedRecord(int indexOfRecord, Person old, Person updatedPerson) {
        records.remove(old);
        records.add(indexOfRecord, updatedPerson);
        System.out.println("Saved");
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

    public void setName(String name) {
        this.name = name;
    }

    public void setName() {
        System.out.println("Enter name:");
        this.setName(sc.nextLine());
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setSurname() {
        System.out.println("Enter surname:");
        this.setSurname(sc.nextLine());
    }

    public void setBirthDate(String birthDate) {
        if (birthDate != null || !birthDate.equals(" ")) {
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
        if (gender.equals("M") || gender.equals("F")) {
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
}
