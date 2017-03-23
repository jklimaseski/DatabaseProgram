package model;

import java.io.Serializable;

/**
 * People. this is the super class to the rest of the data object classes
 */

public class People implements Serializable {

    private String name;
    private Address address;
    private String phone;
    private String ID;

    public People(String name, Address address, String phone, String ID) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

}
