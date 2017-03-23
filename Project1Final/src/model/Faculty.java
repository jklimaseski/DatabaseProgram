package model;

import java.io.Serializable;

/**
 * Faculty. this is the faculty data class super to the part time and full time
 * faculty classes
 */

public class Faculty extends People implements Serializable {

    private String rank;
    private String roomNumber;
    private String officeNumber;
    private String credits;
    private String pay;

    public Faculty(String rank, String roomNumber, String officeNumber, String name, Address address, String phone, String ID, String credits, String pay) {
        super(name, address, phone, ID);
        this.rank = rank;
        this.roomNumber = roomNumber;
        this.officeNumber = officeNumber;
        this.pay = pay;
        this.credits = credits;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

}
