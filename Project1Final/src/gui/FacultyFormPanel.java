package gui;

import gui.inputs.AddressPanel;
import gui.inputs.TeachingCreditsPanel;
import gui.inputs.FacultyTypePanel;

import gui.inputs.IDPanel;
import gui.inputs.NamePanel;
import gui.inputs.OfficePanel;
import gui.inputs.PayPanel;
import gui.inputs.PhoneNumberPanel;
import gui.inputs.RankPanel;
import gui.inputs.RoomPanel;
import javax.swing.JPanel;
import model.FTFaculty;
import model.Faculty;
import model.PTFaculty;
import model.People;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JOptionPane;

/**
 * FacultyFormPanel. this is one of the input panels specifically for the
 * faculty data model it's been formated to make it look better. holds the
 * appropriate input panels for the faculty
 *
 * @author Jason Klimaseski
 */
public class FacultyFormPanel extends JPanel {

    private Faculty temp;

    private final FacultyTypePanel typeBox = new FacultyTypePanel();
    private final NamePanel nameBox = new NamePanel();
    private final RankPanel rankBox = new RankPanel();
    private final RoomPanel roomBox = new RoomPanel();
    private final IDPanel IDBox = new IDPanel();
    private final OfficePanel officeBox = new OfficePanel();
    private final AddressPanel addressBox = new AddressPanel();
    private PayPanel payBox = new PayPanel();
    private final TeachingCreditsPanel creditsBox = new TeachingCreditsPanel(typeBox, payBox);
    private final PhoneNumberPanel phoneBox = new PhoneNumberPanel();

    private DataListener dataListener;

    public FacultyFormPanel() {
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
        add(rankBox, gc);
        gc.gridx++;
        add(IDBox, gc);
        gc.gridx++;
        add(phoneBox, gc);
        gc.gridx++;
        add(addressBox, gc);
        // second row 
        gc.gridy++;
        gc.gridx = 1;
        add(roomBox, gc);
        gc.gridx++;
        add(officeBox, gc);
        gc.gridx++;
        add(creditsBox, gc);
        gc.gridx++;
        add(payBox, gc);

        setVisible(true);
    }

    public Faculty retriveAll() {
        if (creditsBox.getCredits() == null || creditsBox.getCredits().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter data");
            return null;
        } else if (Integer.parseInt(creditsBox.getCredits()) >= 12) {
            return temp = new FTFaculty(creditsBox.getCredits(), rankBox.getRank(), roomBox.getRoom(), officeBox.getOffice(), nameBox.getName(), addressBox.getAddress(), phoneBox.getPhoneNumber(), IDBox.getID(), payBox.getPay());
        } else {
            return temp = new PTFaculty(creditsBox.getCredits(), rankBox.getRank(), roomBox.getRoom(), officeBox.getOffice(), nameBox.getName(), addressBox.getAddress(), phoneBox.getPhoneNumber(), IDBox.getID(), payBox.getPay());
        }

    }

    public void replaceFields(Faculty temp) {
        nameBox.replaceName(temp.getName());
        rankBox.replaceRank(temp.getRank());
        IDBox.replaceID(temp.getID());
        addressBox.replaceAddress(temp.getAddress());
        roomBox.replaceRoom(temp.getRoomNumber());
        officeBox.replaceOffice(temp.getOfficeNumber());
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
