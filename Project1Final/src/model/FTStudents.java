package model;

import java.io.Serializable;
/**
 * FTStudents. the full time student data object
 */

public class FTStudents extends Students implements Serializable{   
       
    public FTStudents(String major, String campus, String name, Address address, String phone, String ID, String credits, String tuiton,String gpa) {
        super(major, campus, name, address, phone, ID, credits, tuiton,gpa);
    }
   
    
   
}
