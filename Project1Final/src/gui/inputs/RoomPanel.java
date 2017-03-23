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
 * RoomPanel. this is one one the input panels they are very similar
 */
public class RoomPanel extends JPanel{
     private JTextArea area;
    
    public RoomPanel(){
        setLayout(new FlowLayout());
        setOpaque(false);
        area = new JTextArea("Room Number",1,6);
        AbstractDocument doc = (AbstractDocument) area.getDocument();
        doc.setDocumentFilter(new PatternFilter("\\d{1,8}",8));
        area = new JTextArea(doc,"Room Number",1,8);
        area.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe) {
                area.selectAll();
            }
            @Override
            public void focusLost(FocusEvent fe) {}
    });
        TitledBorder border =(BorderFactory.createTitledBorder("Room Number"));
        border.setTitleColor(Color.black);
        setBorder(border);
        add(area);
    }
     private String setRoom(){
        area.selectAll();
        return area.getSelectedText();
    }
    public String getRoom(){
        return setRoom();
    }
     public void replaceRoom(String Room){
         area.setText("");
        area.append(Room);
    }
}
