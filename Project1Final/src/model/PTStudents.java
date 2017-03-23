package model;

import java.io.Serializable;

/**
 * PTStudents. this is the part time student data object
 */

public class PTStudents extends Students implements Serializable {

    public PTStudents(String major, String campus, String name, Address address, String phone, String ID, String credits, String tuition, String gpa) {
        super(major, campus, name, address, phone, ID, credits, tuition, gpa);
    }
}
