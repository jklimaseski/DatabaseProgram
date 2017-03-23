package gui.inputs;

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
 * NamePanel. this is one one the input panels they are very similar
 */

public class NamePanel extends JPanel {

    private JTextArea area;

    public NamePanel() {
        setLayout(new FlowLayout());
        setOpaque(false);
        area = new JTextArea("Name", 1, 12);
        AbstractDocument doc = (AbstractDocument) area.getDocument();
        doc.setDocumentFilter(new PatternFilter("\\D{1,25}", 25));
        area = new JTextArea(doc, "Name", 1, 6);
        area.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                area.selectAll();
            }

            @Override
            public void focusLost(FocusEvent fe) {
                if (area.getText().matches("(.*)\\d(.*)")) {
                    area.setForeground(Color.red);
                } else {
                    area.setForeground(Color.black);
                }
            }
        });
        TitledBorder border = (BorderFactory.createTitledBorder("Name"));
        border.setTitleColor(Color.black);
        setBorder(border);
        add(area);
    }

    private String setName() {
        return area.getText();
    }

    public String getName() {
        return setName();
    }

    public void replaceName(String name) {
        area.setText("");
        area.append(name);
    }
}
