package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.Database;
import model.People;

/**
 * StudentTable. this class is the table that contains the student data values
 * it is it's own frame so it can be opened as another window
 *
 */

public class StudentTable extends JFrame {

    private JTable table;
    private StudentTableModel tableModel;
    private JPopupMenu popup;
    private TableListener personTableListener;

    public StudentTable() {
        super("Student Table");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(900, 250);
        tableModel = new StudentTableModel();
        table = new JTable(tableModel);
        popup = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("Delete Row");
        popup.add(removeItem);

        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                table.getSelectionModel().setSelectionInterval(row, row);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popup.show(table, e.getX(), e.getY());
                }
            }
        });

        removeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int row = table.getSelectedRow();
                if (personTableListener != null) {
                    personTableListener.rowDeleted(row);
                    tableModel.fireTableRowsDeleted(row, row);
                }
            }
        });

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        setVisible(false);
    }

    public void setTableListener(TableListener personTableListener) {
        this.personTableListener = personTableListener;
    }

    public void setData(LinkedList db) {
        tableModel.setData(db);
    }

    public void refresh() {
        tableModel.fireTableDataChanged();
    }

    public void close() {
        setVisible(false);
    }

    public void open() {
        setVisible(true);
    }

    public void select(int inter) {
        table.setRowSelectionInterval(inter, inter);
    }
}
