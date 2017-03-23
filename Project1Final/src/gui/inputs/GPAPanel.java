package gui.inputs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

/**
 * GPAPanel. this is one one the input panels they are very similar
 */

public class GPAPanel extends JPanel {

    private JSpinner portSpinner;
    private SpinnerNumberModel spinnerModel;

    public GPAPanel() {
        spinnerModel = new SpinnerNumberModel(3.0, 0, 4.0, .1);
        setOpaque(false);
        portSpinner = new JSpinner(spinnerModel);
        portSpinner.setPreferredSize(new Dimension(40, 25));
        setLayout(new FlowLayout());
        TitledBorder border = (BorderFactory.createTitledBorder("GPA"));
        border.setTitleColor(Color.black);
        setBorder(border);
        add(portSpinner);
    }

    public String getGPA() {
        return String.valueOf(portSpinner.getValue());
    }

    public void replaceGPA(String gpa) {
        portSpinner.setValue(Double.parseDouble(gpa));
    }

}
