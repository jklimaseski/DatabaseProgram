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
 * OfficePanel. this is one one the input panels they are very similar
 */

public class OfficePanel extends JPanel{
     private JTextArea area;
    
    public OfficePanel(){
        setLayout(new FlowLayout());
        setOpaque(false);
        area = new JTextArea("Office",1,6);
        AbstractDocument doc = (AbstractDocument) area.getDocument();
        doc.setDocumentFilter(new PatternFilter("\\d{1,8}",8));
        area = new JTextArea(doc,"Office",1,6);
        area.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe) {
                area.selectAll();
            }
            @Override
            public void focusLost(FocusEvent fe) {}
    });
        TitledBorder border =(BorderFactory.createTitledBorder("Office"+"Number"));
        border.setTitleColor(Color.black);
        setBorder(border);
        add(area);
    }
     private String setOffice(){
        area.selectAll();
        return area.getSelectedText();
    }
    public String getOffice(){
        return setOffice();
    }
     public void replaceOffice(String Office){
         area.setText("");
        area.append(Office);
    }
}
