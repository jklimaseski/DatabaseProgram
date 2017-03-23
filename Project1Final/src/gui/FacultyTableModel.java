package gui;

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;
import model.Faculty;

/**
 * FacultyTableModel. this class is an abstract table model so it dictates what
 * the faculty table is to look like and contain
 */

public class FacultyTableModel extends AbstractTableModel {

    private LinkedList<Faculty> db;
    private String[] colNames = {"ID", "Faculty Type", "Name", "Credits", "Rank", "Room_number",
        "Office Number", "Street", "State", "Zip", "Phone", "Pay"};

    public FacultyTableModel() {

    }

    public String getColumnName(int column) {
        return colNames[column];
    }

    public void setData(LinkedList db) {
        this.db = db;
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public int getRowCount() {
        return db.size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        Faculty people = db.get(row);
        switch (col) {
            case 0:
                return people.getID();
            case 1:
                if (people.getCredits() == null || people.getCredits().equals("")) {
                    return null;
                } else if (Integer.parseInt(people.getCredits()) >= 12) {
                    return "Full time";
                } else {
                    return "Part Time";
                }
            case 2:
                return people.getName();
            case 3:
                return people.getCredits();
            case 4:
                return people.getRank();
            case 5:
                return people.getRoomNumber();
            case 6:
                return people.getOfficeNumber();
            case 7:
                return people.getAddress().getRoad();
            case 8:
                return people.getAddress().getState();
            case 9:
                return people.getAddress().getZip();
            case 10:
                return people.getPhone();
            case 11:
                return people.getPay();
        }
        return null;
    }

}
