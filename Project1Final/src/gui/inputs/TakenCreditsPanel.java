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
 * TakenCreditsPanel. this is one one the input panels they are very similar
 */
public class TakenCreditsPanel extends JPanel {

    private JTextArea area;
    private final double PRICE_PER_CREDIT = 180.0;

    public TakenCreditsPanel(final TuitionPanel tuitionBox, final StudentsTypePanel typeBox) {
        setLayout(new FlowLayout());
        setOpaque(false);
        area = new JTextArea("Credits", 1, 6);
        AbstractDocument doc = (AbstractDocument) area.getDocument();
        doc.setDocumentFilter(new PatternFilter("\\d{1,9}", 8));
        area = new JTextArea(doc, "Credits", 1, 6);
        area.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                area.selectAll();
            }

            @Override
            public void focusLost(FocusEvent fe) {
                if (area.getText() == null || area.getText().equals("")) {
                    return;
                } else if (Double.parseDouble(area.getText()) >= 12) {
                    typeBox.isFT();
                    tuitionBox.replaceTuiton("4200");
                } else if (area.getText() != null) {
                    typeBox.isPT();
                    tuitionBox.replaceTuiton(String.valueOf((int) ((Double.parseDouble(area.getText()) * PRICE_PER_CREDIT))));
                }
            }
        });
        TitledBorder border = (BorderFactory.createTitledBorder("Credits"));
        border.setTitleColor(Color.black);
        setBorder(border);
        add(area);
    }

    private String setCredits() {
        area.selectAll();
        return area.getSelectedText();
    }

    public String getCredits() {
        return setCredits();
    }

    public void replaceCredits(String credits) {
        area.setText("");
        area.append(credits);
    }
}
