package model;

import java.io.Serializable;
/**
 * FTFaculty. this class
 * @author teep
 */
public class FTFaculty extends Faculty implements Serializable{    

    public FTFaculty(String credits, String rank, String roomNumber, String officeNumber, String name, Address address, String phone, String ID, String pay) {
        super(rank, roomNumber, officeNumber, name, address, phone, ID, credits, pay);
    }   
}
