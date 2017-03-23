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
 * TeachingCreditsPanel. this is one one the input panels they are very similar
 */
public class TeachingCreditsPanel extends JPanel {

    private JTextArea area;
    private final double PAY_PER_CREDIT = 1000.0;

    public TeachingCreditsPanel(final FacultyTypePanel typeBox, final PayPanel payBox) {
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
                    payBox.replacePay("10000");
                } else {
                    typeBox.isPT();
                    payBox.replacePay(String.valueOf((int) (Double.parseDouble(area.getText()) * PAY_PER_CREDIT)));
                }
            }
        });
        TitledBorder border = (BorderFactory.createTitledBorder("Credits"));
        border.setTitleColor(Color.black);
        setBorder(border);
        add(area);
    }

    public String getCredits() {
        return area.getText();
    }

    public void replaceCredits(String credits) {
        area.setText("");
        area.append(credits);
    }
}
