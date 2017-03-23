package gui.inputs;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

/**
 * StudentsTypePanel. this is one one the input panels they are very similar
 */

public class StudentsTypePanel extends JPanel {

    private ButtonGroup group = new ButtonGroup();
    private JRadioButton fullTime;
    private JRadioButton partTime;

    public StudentsTypePanel() {
        fullTime = new JRadioButton("Full Time");
        partTime = new JRadioButton("Part Time");
        setLayout(new FlowLayout());
        fullTime.setOpaque(false);
        partTime.setOpaque(false);
        fullTime.setEnabled(false);
        partTime.setEnabled(false);
        setOpaque(false);
        TitledBorder border = (BorderFactory.createTitledBorder("Student Type"));
        border.setTitleColor(Color.black);
        setBorder(border);
        group.add(fullTime);
        group.add(partTime);
        add(fullTime);
        add(partTime);

    }

    public void isFT() {
        partTime.setSelected(false);
        fullTime.setSelected(true);
    }

    public void isPT() {
        fullTime.setSelected(false);
        partTime.setSelected(true);
    }
}
