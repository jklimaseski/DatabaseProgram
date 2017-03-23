package gui;

import gui.inputs.AddressPanel;
import gui.inputs.CampusPanel;
import gui.inputs.GPAPanel;
import gui.inputs.IDPanel;
import gui.inputs.MajorPanel;
import gui.inputs.NamePanel;
import gui.inputs.PhoneNumberPanel;
import gui.inputs.StudentsTypePanel;
import gui.inputs.TakenCreditsPanel;
import gui.inputs.TuitionPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.FTStudents;
import model.PTStudents;
import model.People;
import model.Students;

/**
 * StudentsFormPanel. this is one of the input panels specifically for the
 * students data model it's been formated to make it look better. holds the
 * appropriate input panels for the students
 *
 * @author Jason Klimaseski
 */
public class StudentsFormPanel extends JPanel {

    private Students temp;

    private final StudentsTypePanel typeBox = new StudentsTypePanel();
    private final NamePanel nameBox = new NamePanel();
    private final GPAPanel GPABox = new GPAPanel();
    private final MajorPanel majorBox = new MajorPanel();
    private final IDPanel IDBox = new IDPanel();
    private final CampusPanel campusBox = new CampusPanel();
    private final AddressPanel addressBox = new AddressPanel();
    private final TuitionPanel tuitionBox = new TuitionPanel();
    private final TakenCreditsPanel creditsBox = new TakenCreditsPanel(tuitionBox, typeBox);
    private final PhoneNumberPanel phoneBox = new PhoneNumberPanel();

    private DataListener dataListener;

    public StudentsFormPanel() {
        setOpaque(false);
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridy = 0;
        gc.weightx = .11;
        gc.weighty = 0;
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        /// first row
        add(typeBox, gc);
        gc.gridx++;
        add(nameBox, gc);
        gc.gridx++;
        add(GPABox, gc);
        gc.gridx++;
        add(IDBox, gc);
        gc.gridx++;
        add(phoneBox, gc);
        gc.gridx++;
        add(addressBox, gc);
        // second row 
        gc.gridy++;
        gc.gridx = 1;
        add(majorBox, gc);
        gc.gridx++;
        add(campusBox, gc);
        gc.gridx++;
        add(creditsBox, gc);
        gc.gridx++;
        add(tuitionBox, gc);
        setVisible(true);
    }

    public Students retriveAll() {
        if (creditsBox.getCredits() == null || creditsBox.getCredits().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter data");
            return null;
        } else if (Integer.parseInt(creditsBox.getCredits()) >= 12) {
            return temp = new FTStudents(majorBox.getMajor(), campusBox.getCampus(), nameBox.getName(), addressBox.getAddress(), phoneBox.getPhoneNumber(), IDBox.getID(), creditsBox.getCredits(), tuitionBox.getTuiton(), (String) GPABox.getGPA());
        } else {
            return temp = new PTStudents(majorBox.getMajor(), campusBox.getCampus(), nameBox.getName(), addressBox.getAddress(), phoneBox.getPhoneNumber(), IDBox.getID(), creditsBox.getCredits(), tuitionBox.getTuiton(), (String) GPABox.getGPA());
        }

    }

    public void replaceFields(Students temp) {
        nameBox.replaceName(temp.getName());
        GPABox.replaceGPA(temp.getGpa());
        IDBox.replaceID(temp.getID());
        majorBox.replaceMajor(temp.getMajor());
        campusBox.replaceCampus(temp.getCampus());
        addressBox.replaceAddress(temp.getAddress());
        tuitionBox.replaceTuiton(temp.getTuition());
        creditsBox.replaceCredits(temp.getCredits());
        phoneBox.replacePhoneNumber(temp.getPhone());
    }

    public DataListener getDataListener() {
        return dataListener;
    }

    public void setDataLIstener(DataListener dataListener) {
        this.dataListener = dataListener;
    }
}
