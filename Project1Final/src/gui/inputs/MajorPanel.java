package gui.inputs;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * MajorPanel. this is one one the input panels they are very similar
 */

public class MajorPanel extends JPanel {

    private JComboBox area;
    private String[] major = {"CS", "IT", "MA"};

    public MajorPanel() {
        setLayout(new FlowLayout());
        setOpaque(false);
        area = new JComboBox(major);
        TitledBorder border = (BorderFactory.createTitledBorder("Major"));
        border.setTitleColor(Color.black);
        setBorder(border);
        area.setEditable(true);
        add(area);
    }

    public String getMajor() {
        return (String) area.getSelectedItem();
    }

    public void replaceMajor(String camp) {
        for (int i = 0; i < 3; i++) {
            if (camp.equals(major[i]) == true) {
                area.setSelectedIndex(i);
            }
        }
    }
}
