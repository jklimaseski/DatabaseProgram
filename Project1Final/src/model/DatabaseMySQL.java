package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

/**
 * DatabaseMySQL. this class handles moving data into the mysql database and
 * tables as well as saving the mysql database to file
 */
public class DatabaseMySQL {

    private Database data;
    private List<Students> students;
    private List<Faculty> faculty;

    private Connection con;

    public DatabaseMySQL(Database data) {
        this.data = data;
        students = data.getStudentList();
        faculty = data.getFacultyList();
    }

    public void connect() throws Exception {

        if (con != null) {
            return;
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new Exception("Driver not found");
        }

        String url = "jdbc:mysql://localhost:3306/people";
        System.out.println("Connected");

        con = DriverManager.getConnection(url, "root", "student");
    }

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Can't close connection");
            }
        }
    }

    public void save() throws SQLException {

        String checkStudentsSql = "select count(*) as count from students where id=?";
        PreparedStatement checkStudentStmt = con.prepareStatement(checkStudentsSql);
        String checkFacultySql = "select count(*) as count from faculty where id=?";
        PreparedStatement checkFacultyStmt = con.prepareStatement(checkFacultySql);

		//int id = person.getId();
		//checkStmt.setInt(1, id);
        String insertStudentSql = "insert into students (ID, student_name, credit,major,campus,street,state,zip,phone,Tuition,GPA) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
        PreparedStatement insertStudentStatement = con.prepareStatement(insertStudentSql);
        String insertFacultySql = "insert into faculty (ID, faculty_name,credit, room_number,office_number,street,state,zip,phone,pay,rank ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
        PreparedStatement insertFacultyStatement = con.prepareStatement(insertFacultySql);

        String updateStudentSql = "update students set ID = ?,student_name=?, credit=?, major=?, campus=?, street=?,state=?,zip=? phone=?, Tuition=?, GPA=? where ID=?";
        PreparedStatement updateStudentStatement = con.prepareStatement(updateStudentSql);
        String updateFacultySql = "update faculty set ID = ?,faculty_name=?, credit=?, room_number=?, office_number=?, street=?,state=?,zip=? phone=?, pay=?, rank=? where ID=?";
        PreparedStatement updateFacultyStatement = con.prepareStatement(updateFacultySql);

        for (Students student : students) {
            String ID = student.getID();
            String name = student.getName();
            String credits = student.getCredits();
            String major = student.getMajor();
            String campus = student.getCampus();
            Address address = student.getAddress();
            String phone = student.getPhone();
            String tuition = student.getTuition();
            String GPA = student.getGpa();

            checkStudentStmt.setInt(1, Integer.parseInt(ID));
            ResultSet checkResult = checkStudentStmt.executeQuery();
            checkResult.next();

            int count = checkResult.getInt(1);

            if (count == 0) {
                System.out.println("Inserting student with ID " + ID);

                int col = 1;
                insertStudentStatement.setString(col++, ID);
                insertStudentStatement.setString(col++, name);
                insertStudentStatement.setString(col++, credits);
                insertStudentStatement.setString(col++, major);
                insertStudentStatement.setString(col++, campus);
                insertStudentStatement.setString(col++, address.getRoad());
                insertStudentStatement.setString(col++, address.getState());
                insertStudentStatement.setString(col++, address.getZip());
                insertStudentStatement.setString(col++, phone);
                insertStudentStatement.setString(col++, tuition);
                insertStudentStatement.setString(col++, GPA);

                insertStudentStatement.executeUpdate();
            } else {
                System.out.println("Updating student with ID " + ID);
                int col = 1;

                updateStudentStatement.setString(col++, name);
                updateStudentStatement.setString(col++, credits);
                updateStudentStatement.setString(col++, major);
                updateStudentStatement.setString(col++, campus);
                updateStudentStatement.setString(col++, address.getRoad());
                updateStudentStatement.setString(col++, address.getState());
                updateStudentStatement.setString(col++, address.getZip());
                updateStudentStatement.setString(col++, phone);
                updateStudentStatement.setString(col++, ID);
                updateStudentStatement.setString(col++, tuition);
                updateStudentStatement.setString(col++, GPA);

                updateStudentStatement.executeUpdate();
            }
        }
        for (Faculty facultys : faculty) {// faculty (ID, name,credit, room_number,office_number,street,state,zip,phone,pay,rank )
            String ID = facultys.getID();
            String name = facultys.getName();
            String credits = facultys.getCredits();
            String room = facultys.getRoomNumber();
            String office = facultys.getOfficeNumber();
            Address address = facultys.getAddress();
            String phone = facultys.getPhone();
            String pay = facultys.getPay();
            String rank = facultys.getRank();

            checkFacultyStmt.setInt(1, Integer.parseInt(ID));
            ResultSet checkResult = checkFacultyStmt.executeQuery();
            checkResult.next();

            int count = checkResult.getInt(1);

            if (count == 0) {
                System.out.println("Inserting faculty with ID " + ID);

                int col = 1;
                insertFacultyStatement.setString(col++, ID);
                insertFacultyStatement.setString(col++, name);
                insertFacultyStatement.setString(col++, credits);
                insertFacultyStatement.setString(col++, room);
                insertFacultyStatement.setString(col++, office);
                insertFacultyStatement.setString(col++, address.getRoad());
                insertFacultyStatement.setString(col++, address.getState());
                insertFacultyStatement.setString(col++, address.getZip());
                insertFacultyStatement.setString(col++, phone);
                insertFacultyStatement.setString(col++, pay);
                insertFacultyStatement.setString(col++, rank);

                insertFacultyStatement.executeUpdate();
            } else {
                System.out.println("Updating faculty with ID " + ID);
                int col = 1;

                updateFacultyStatement.setString(col++, ID);
                updateFacultyStatement.setString(col++, name);
                updateFacultyStatement.setString(col++, credits);
                updateFacultyStatement.setString(col++, room);
                updateFacultyStatement.setString(col++, office);
                updateFacultyStatement.setString(col++, address.getRoad());
                updateFacultyStatement.setString(col++, address.getState());
                updateFacultyStatement.setString(col++, address.getZip());
                updateFacultyStatement.setString(col++, phone);
                updateFacultyStatement.setString(col++, pay);
                updateFacultyStatement.setString(col++, rank);

                updateFacultyStatement.executeUpdate();
            }
        }
        updateFacultyStatement.close();
        insertFacultyStatement.close();
        checkFacultyStmt.close();
    }

    public void load() throws SQLException {
        students.clear();
        faculty.clear();
        String sqlStudent = "select ID, student_name, credits, major, campus, street, state, zip,phone,tuition,GPA from students order by student_name";
        String sqlFaculty = "select ID, faculty_name, credits, room_number, office_number, street, state, zip,phone,pay,rank from faculty order by faculty_name";
        Statement selectStatement = con.createStatement();
        ResultSet resultsStudent = selectStatement.executeQuery(sqlStudent);
        ResultSet resultsFaculty = selectStatement.executeQuery(sqlFaculty);
        while (resultsStudent.next()) {
            String ID = resultsStudent.getString("ID");
            String name = resultsStudent.getString("name");
            String credits = resultsStudent.getString("credits");
            String major = resultsStudent.getString("major");
            String campus = resultsStudent.getString("campus");
            String street = resultsStudent.getString("street");
            String state = resultsStudent.getString("state");
            String zip = resultsStudent.getString("zip");
            String phone = resultsStudent.getString("phone");            
            String tuition = resultsStudent.getString("tuition");
            String GPA = resultsStudent.getString("GPA");
            Address address = new Address(street, state, zip);
            Students student = new Students(major, campus, name, address, phone, ID, credits, tuition, GPA);
            students.add(student);
            System.out.println(student);
        }
        while (resultsFaculty.next()) {
          
            String ID = resultsStudent.getString("ID");
            String name = resultsStudent.getString("name");
            String credits = resultsStudent.getString("credits");
            String room = resultsStudent.getString("room_number");
            String office = resultsStudent.getString("office_number");
            String street = resultsStudent.getString("street");
            String state = resultsStudent.getString("state");
            String zip = resultsStudent.getString("zip");
            String phone = resultsStudent.getString("phone");            
            String pay = resultsStudent.getString("pay");
            String rank = resultsStudent.getString("rank");
            Address address = new Address(street, state, zip);
            Faculty facultyObj = new Faculty(rank, room,office, name, address, phone, ID, credits, pay);
            faculty.add(facultyObj);
            System.out.println(facultyObj);
        }

        resultsStudent.close();
        resultsFaculty.close();
        selectStatement.close();

    }

    public void addStudent(Students student) {
        students.add(student);
    }
     public void addFaculty(Faculty fact) {
        faculty.add(fact);
    }

    public void removeStudent(int index) {
        students.remove(index);
    }
    public void removeFaculty(int index) {
        faculty.remove(index);
    }

    public List<Students> getStudents() {
        return Collections.unmodifiableList(students);
    }
    public List<Faculty> getFaculty() {
        return Collections.unmodifiableList(faculty);
    }

    public void saveToFile(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(data);

        oos.close();
    }

    public void loadFromFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            data = (Database) ois.readObject();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ois.close();
    }
}
