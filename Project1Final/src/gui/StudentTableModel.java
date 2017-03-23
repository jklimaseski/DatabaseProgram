package gui;

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;
import model.Database;
import model.FTStudents;
import model.Students;

/**
 * StudentTableModel. this class is an abstract table model so it dictates what
 * the student table is to look like and contain
 */

public class StudentTableModel extends AbstractTableModel {

    private LinkedList<Students> db;
    private String[] colNames = {"ID", "Student Type", "Name", "Credits", "GPA", "Major", "Campus", "Street", "State", "Zip", "Phone Number", "Tuiton"};

    public StudentTableModel() {

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
        Students people = db.get(row);
        switch (col) {
            case 0:
                return people.getID();
            case 1:
                if (Integer.parseInt(people.getCredits()) >= 12) {
                    return "Full time";
                } else {
                    return "Part Time";
                }
            case 2:
                return people.getName();
            case 3:
                return people.getCredits();
            case 4:
                return people.getGpa();
            case 5:
                return people.getMajor();
            case 6:
                return people.getCampus();
            case 7:
                return people.getAddress().getRoad();
            case 8:
                return people.getAddress().getState();
            case 9:
                return people.getAddress().getZip();
            case 10:
                return people.getPhone();
            case 11:
                return people.getTuition();
        }
        return null;
    }

}
