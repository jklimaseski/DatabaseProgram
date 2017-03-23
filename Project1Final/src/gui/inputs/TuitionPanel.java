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
 * TuitionPanel. this is one one the input panels they are very similar
 */

public class TuitionPanel extends JPanel {

    private JTextArea area;

    public TuitionPanel() {
        setLayout(new FlowLayout());
        setOpaque(false);
        area = new JTextArea("Tuition", 1, 6);
        AbstractDocument doc = (AbstractDocument) area.getDocument();
        doc.setDocumentFilter(new PatternFilter("\\d{1,8}", 8));
        area = new JTextArea(doc, "Tuition", 1, 6);
        area.setEnabled(false);
        area.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                area.selectAll();
            }

            @Override
            public void focusLost(FocusEvent fe) {
            }
        });
        TitledBorder border = (BorderFactory.createTitledBorder("Tuition"));
        border.setTitleColor(Color.black);
        setBorder(border);
        add(area);
    }

    public String getTuiton() {
        return area.getText();
    }

    public void replaceTuiton(String Tuiton) {
        area.setText("");
        area.setText(Tuiton);
    }
}
