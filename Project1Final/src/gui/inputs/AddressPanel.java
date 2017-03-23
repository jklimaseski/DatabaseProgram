package gui.inputs;

import java.awt.Color;
import model.Address;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;

/**
 * AddressPanel. this is one one the input panels they are very similar this one uses the class address to store all the address info
 */

public class AddressPanel extends JPanel {

    private JComboBox jcBox;
    private JTextArea address;
    private JTextArea zip;
    private Address value;
    private String[] states = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI",
        "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE",
        "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX",
        "UT", "VT", "VA", "WA", "WV", "WI", "WY"};

    public AddressPanel() {
        setLayout(new FlowLayout());
        setOpaque(false);
        address = new JTextArea("Street", 1, 20);
        address.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                address.selectAll();
            }

            @Override
            public void focusLost(FocusEvent fe) {
            }
        });
        zip = new JTextArea();
        AbstractDocument doc = (AbstractDocument) zip.getDocument();
        doc.setDocumentFilter(new PatternFilter(".{1,5}", 5));
        zip = new JTextArea(doc, "Zip", 1, 5);
        zip.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                zip.selectAll();
            }

            @Override
            public void focusLost(FocusEvent fe) {
                if ((!(zip.getText().matches("(.*)\\d(.*)"))) || zip.getText().length() < 5) {
                    zip.setForeground(Color.red);
                } else {
                    zip.setForeground(Color.black);
                }
            }
        });
        jcBox = new JComboBox(states);
        TitledBorder border = (BorderFactory.createTitledBorder("Address"));
        border.setTitleColor(Color.black);
        setBorder(border);
        jcBox.setEditable(false);
        add(address);
        add(jcBox);
        add(zip);
    }

    public Address getAddress() {
        value = new Address(getAddressArea(), getJCBox(), getZip());
        return value;
    }

    private String getJCBox() {
        return (String) jcBox.getSelectedItem();
    }

    private String getAddressArea() {
        address.selectAll();
        return address.getSelectedText();
    }

    private String getZip() {
        zip.selectAll();
        return zip.getSelectedText();
    }

    public void replaceAddress(Address temp) {
        address.setText("");
        zip.setText("");
        address.append(temp.getRoad());
        zip.append(temp.getZip());
        for (int i = 0; i < 50; i++) {

            if ((temp.getState()).equals(states[i]) == true) {
                jcBox.setSelectedIndex(i);
            }
        }
    }
}
