package gui.inputs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;

/**
 * PhoneNumberPanel. this is one one the input panels they are very similar
 */

public class PhoneNumberPanel extends JPanel {

    private JTextArea area;

    public PhoneNumberPanel() {
        setLayout(new BorderLayout());
        setOpaque(false);
        area = new JTextArea();
        AbstractDocument doc = (AbstractDocument) area.getDocument();
        doc.setDocumentFilter(new PatternFilter("\\d{1,10}", 10));
        area = new JTextArea(doc, "Phone Number", 1, 10);
        area.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                area.selectAll();
            }

            @Override
            public void focusLost(FocusEvent fe) {
                if (area.getText().length() < 10) {
                    area.setForeground(Color.red);
                } else {
                    area.setForeground(Color.black);
                }
            }
        });
        TitledBorder border = (BorderFactory.createTitledBorder("Phone Number"));
        border.setTitleColor(Color.black);
        setBorder(border);
        add(area);
    }

    private String setPhoneNumber() {
        area.selectAll();
        return area.getSelectedText();
    }

    public String getPhoneNumber() {
        return setPhoneNumber();
    }

    public void replacePhoneNumber(String gpa) {
        area.setText("");
        area.append(gpa);
    }
}
