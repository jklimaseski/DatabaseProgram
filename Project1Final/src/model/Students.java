package model;

import java.io.Serializable;

/**
 * Students.this is the super class to the part time and full time students
 * object classes
 */

public class Students extends People implements Serializable {

    private String major;
    private String campus;
    private String credits;
    private String tuition;
    private String gpa;

    public Students(String major, String campus, String name, Address address, String phone, String ID, String credits, String tuition, String gpa) {
        super(name, address, phone, ID);
        this.major = major;
        this.campus = campus;
        this.credits = credits;
        this.tuition = tuition;
        this.gpa = gpa;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getTuition() {
        return tuition;
    }

    public void setTuiton(String tuiton) {
        this.tuition = tuiton;
    }

}
