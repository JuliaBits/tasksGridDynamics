package task3;

import java.time.LocalDateTime;

public class Organization extends Record {
    private String orgName;
    private String address;

    public Organization createRecord() {
        Organization newOrganization = new Organization();
        newOrganization.setOrgName();
        newOrganization.setAddress();
        newOrganization.setNumber();
        newOrganization.setCreated(LocalDateTime.now().withNano(0));
        newOrganization.setLastEdit(LocalDateTime.now().withNano(0));
        return newOrganization;
    }

    public String organizationInfo() {
        return "Organization name: " + orgName + "\n" +
                "Address: " + address + "\n" +
                "Number: " + super.getNumber() + "\n" +
                "Time created: " + super.getCreated() + "\n" +
                "Time last edit: " + super.getLastEdit();
    }

    public void edit(Record record) {
        Organization old = (Organization) record;
        System.out.println("Select a field (name, address, number):");
        String fieldToUpdate = sc.nextLine();
        switch (fieldToUpdate) {
            case "name": {
                old.setOrgName();
                break;
            }
            case "address": {
                old.setAddress();
                break;
            }
            case "number": {
                old.setNumber();
                break;
            }
        }
        old.setLastEdit(LocalDateTime.now().withNano(0));
        System.out.println("Saved!");
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setOrgName() {
        System.out.println("Enter organization name:");
        this.setOrgName(sc.nextLine());
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddress() {
        System.out.println("Enter address:");
        this.setAddress(sc.nextLine());
    }

    public String getOrgName() {
        return orgName;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return orgName;
    }
}
