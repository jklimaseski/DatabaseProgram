package model;

import java.io.Serializable;

/**
 * Address. this is an object that holder all the address data
 */

public class Address implements Serializable {

    private String road;
    private String state;
    private String zip;

    public Address(String road, String state, String zip) {
        this.road = road;
        this.state = state;
        this.zip = zip;
    }

    public String getRoad() {
        return road;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

}
