package gui.inputs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;

/**
 * IDPanel. this is one one the input panels they are very similar
 */

public class IDPanel extends JPanel {

    private JTextArea area;

    public IDPanel() {
        setOpaque(false);
        setLayout(new BorderLayout());
        area = new JTextArea();
        AbstractDocument doc = (AbstractDocument) area.getDocument();
        doc.setDocumentFilter(new PatternFilter("\\d{1,8}", 8));
        area = new JTextArea(doc, "ID", 1, 8);
        area.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                area.selectAll();
            }

            @Override
            public void focusLost(FocusEvent fe) {
                if (area.getText().length() < 8) {
                    area.setForeground(Color.red);
                } else {
                    area.setForeground(Color.black);
                }
            }
        });
        TitledBorder border = (BorderFactory.createTitledBorder("ID"));
        border.setTitleColor(Color.black);
        setBorder(border);
        add(area);
    }

    private String setID() {
        area.selectAll();
        return area.getSelectedText();
    }

    public String getID() {
        return setID();
    }

    public void replaceID(String id) {
        area.setText("");
        area.append(id);
    }
}
