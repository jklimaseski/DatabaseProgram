package model;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Database. this is the database class it handles all the data including search
 * and storage for saving and loading to file, likely has erroneous methods.
 */
public class Database implements Serializable {

    LinkedList<LinkedList> struct = new LinkedList<LinkedList>();
    private People node;
    private LinkedList<Students> studentList;
    private LinkedList<Faculty> facultyList;

    public Database(LinkedList<Students> studentList, LinkedList<Faculty> facultyList) {
        this.studentList = studentList;
        this.facultyList = facultyList;
        struct.add(this.studentList);
        struct.add(this.facultyList);
    }

    public LinkedList getDatabase() {
        return struct;
    }

    public void setDatabase(LinkedList students, LinkedList faculty) {
        studentList = students;
        facultyList = faculty;
    }

    public LinkedList<Students> getStudentList() {
        return studentList;
    }

    public LinkedList<Faculty> getFacultyList() {
        return facultyList;
    }

    public double[] search(String targetKey) {
        double[] info = null;
        if (struct.size() == 0) {
            return info;
        }
        for (int i = 0; i < struct.get(0).size(); i++) {
            node = (People) (struct.get(0).get(i));
            if (node.getID().equals(targetKey)) {
                info = new double[]{0, i};
                return info;
            }
        }
        for (int i = 0; i < struct.get(1).size(); i++) {
            node = (People) (struct.get(1).get(i));
            if (node.getID().equals(targetKey)) {
                info = new double[]{1, i};
                return info;
            }
        }
        return info;
    }

    public void clear() {
        struct.clear();
    }

    public void addAll(LinkedList people) {
        studentList = (LinkedList<Students>) people.getFirst();
        System.out.println(people.toString());
        System.out.println(people.getFirst().toString());
        facultyList = (LinkedList<Faculty>) people.getLast();

    }
}
