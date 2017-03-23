package gui.inputs;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * RankPanel. this is one one the input panels they are very similar
 */

public class RankPanel extends JPanel {

    private JTextArea area;

    public RankPanel() {
        setLayout(new FlowLayout());
        setOpaque(false);
        area = new JTextArea("Rank", 1, 6);
        area.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                area.selectAll();
            }

            @Override
            public void focusLost(FocusEvent fe) {
            }
        });
        TitledBorder border = (BorderFactory.createTitledBorder("Rank"));
        border.setTitleColor(Color.black);
        setBorder(border);
        add(area);
    }

    private String setRank() {
        area.selectAll();
        return area.getSelectedText();
    }

    public String getRank() {
        return setRank();
    }

    public void replaceRank(String Rank) {
        area.setText("");
        area.append(Rank);
    }
}
