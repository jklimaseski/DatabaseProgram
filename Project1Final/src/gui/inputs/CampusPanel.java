package gui.inputs;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * CampusPanel. this is one one the input panels they are very similar
 */

public class CampusPanel extends JPanel {

    private JComboBox area;
    private String[] campus = {"Eastern", "Ammerman", "Grant"};

    public CampusPanel() {
        setLayout(new FlowLayout());
        setOpaque(false);
        area = new JComboBox(campus);
        TitledBorder border = (BorderFactory.createTitledBorder("Campus"));
        border.setTitleColor(Color.black);
        setBorder(border);
        area.setEditable(false);
        add(area);
    }

    public String getCampus() {
        return (String) area.getSelectedItem();
    }

    public void replaceCampus(String camp) {
        for (int i = 0; i < 3; i++) {
            if (camp.equals(campus[i]) == true) {
                area.setSelectedIndex(i);
            }
        }
    }
}
