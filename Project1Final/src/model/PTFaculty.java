package model;

import java.io.Serializable;

/**
 * PTFaculty. this is the part time faculty class.
 */

public class PTFaculty extends Faculty implements Serializable {

    public PTFaculty(String credits, String rank, String roomNumber, String officeNumber, String name, Address address, String phone, String ID, String pay) {
        super(rank, roomNumber, officeNumber, name, address, phone, ID, credits, pay);
    }
}
