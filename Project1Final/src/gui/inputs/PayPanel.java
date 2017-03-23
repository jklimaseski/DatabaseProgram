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
 * PayPanel. this is one one the input panels they are very similar
 */
public class PayPanel extends JPanel {

    private JTextArea area;

    public PayPanel() {
        setLayout(new FlowLayout());
        setOpaque(false);
        area = new JTextArea("Pay", 1, 6);
        area.setEnabled(false);
        AbstractDocument doc = (AbstractDocument) area.getDocument();
        doc.setDocumentFilter(new PatternFilter("\\d{1,8}", 8));
        area = new JTextArea(doc, "Pay", 1, 6);
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
        TitledBorder border = (BorderFactory.createTitledBorder("Pay"));
        border.setTitleColor(Color.black);
        setBorder(border);
        add(area);
    }

    private String setPay() {
        return area.getText();
    }

    public String getPay() {
        return setPay();
    }

    public void replacePay(String pay) {
        area.setText("");
        area.setText(pay);
    }
}
