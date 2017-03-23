package gui;

import gui.fileFilters.BinaryFileFilter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import model.Database;
import model.DatabaseMySQL;
import model.Faculty;
import model.People;
import model.Students;

/**
 * Mainframe. this is the mainframe where all the data and panels meet together
 * to create the project
 *
 * @author Jason Klimaseski
 */
public class MainFrame extends JFrame {

    private LinkedList<Students> studentList = new LinkedList<Students>();
    private LinkedList<Faculty> facultyList = new LinkedList<Faculty>();
    private Database data = new Database(studentList, facultyList);
    private People node;
    private JButton add;
    private JButton edit;
    private JButton delete;
    private Buttons buttons;
    private boolean check;
    private StudentTable studentTable = new StudentTable();
    private FacultyTable facultyTable = new FacultyTable();
    private DataListener dataListener;
    private JCheckBoxMenuItem studentTableItem;
    private JCheckBoxMenuItem facultyTableItem;
    private JCheckBox update;
    private boolean isStudent;
    private double[] index;
    private PrefsDialog prefsDialog;

    private MenuBar menuBar = new MenuBar();
    private ButtonGroup group = new ButtonGroup();
    private JRadioButton student;
    private JRadioButton faculty;
    private FacultyFormPanel facultyFormPanel = new FacultyFormPanel();
    private StudentsFormPanel studentsFormPanel = new StudentsFormPanel();
    private GridBagConstraints gc = new GridBagConstraints();

    public MainFrame() {
        super("System");
        data.setDatabase(studentList, facultyList);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//makeing it close on exiting window       
        setSize(850, 600);

        setLayout(new BorderLayout());// background
        setContentPane(new JLabel(new ImageIcon("background2.jpg")));
        setLayout(new FlowLayout());
        setSize(1100, 550);

        setLayout(new BorderLayout());

        prefsDialog = new PrefsDialog(this);

        setJMenuBar(menuBar.buildMenuBar());

        student = new JRadioButton("Student", false);
        group.add(student);
        student.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                remove(facultyFormPanel);
                revalidate();
                repaint();
                add(studentsFormPanel, BorderLayout.EAST);
                revalidate();
                repaint();
                check = true;
            }
        });

        faculty = new JRadioButton("Faculty", false);
        group.add(faculty);
        faculty.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                remove(studentsFormPanel);
                revalidate();
                repaint();
                add(facultyFormPanel, BorderLayout.EAST);
                revalidate();
                repaint();
                check = false;
            }
        });

        faculty.setOpaque(false);
        faculty.setForeground(Color.black);
        student.setOpaque(false);
        student.setForeground(Color.black);

        update = new JCheckBox("Edit");
        update.setOpaque(false);
        edit = new JButton("Edit Entry");
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (check == true) {
                    studentList.remove((int) index[1]);
                    studentList.add((int) index[1], (Students) studentsFormPanel.retriveAll());
                    studentTable.refresh();
                    update.setVisible(false);
                    update.setSelected(false);
                    add.setVisible(true);
                    delete.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Entry edited");
                } else if (check == false) {
                    facultyList.remove((int) index[1]);
                    facultyList.add((int) index[1], (Faculty) facultyFormPanel.retriveAll());
                    facultyTable.refresh();
                    update.setVisible(false);
                    update.setSelected(false);
                    add.setVisible(true);
                    delete.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Entry edited");
                } else {
                    update.setVisible(false);
                    update.setSelected(false);
                    add.setVisible(true);
                    delete.setVisible(false);
                    JOptionPane.showMessageDialog(null, "No data to be edited");
                }
            }

        });
        add = new JButton("Add Entry");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (check == true) {
                    studentList.add((Students) studentsFormPanel.retriveAll());
                    studentTable.refresh();
                    JOptionPane.showMessageDialog(null, "Input added");
                } else if (check == false) {
                    facultyList.add((Faculty) facultyFormPanel.retriveAll());
                    facultyTable.refresh();
                    JOptionPane.showMessageDialog(null, "Input added");
                } else {
                    JOptionPane.showMessageDialog(null, "No data to be inserted");
                }
            }

        });
        delete = new JButton("Remove Entry");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (index[0] == 0) {
                    int i = (int) index[1];
                    studentList.remove(i);
                    studentTable.refresh();
                    delete.setVisible(false);
                    update.setVisible(false);
                    update.setSelected(false);
                    add.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Entry deleted");
                } else if (index[0] == 1) {
                    int i = (int) index[1];
                    facultyList.remove(i);
                    facultyTable.refresh();
                    delete.setVisible(false);
                    update.setVisible(false);
                    update.setSelected(false);
                    add.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Entry deleted");
                } else {
                    JOptionPane.showMessageDialog(null, "No data to be deleted.");
                    delete.setVisible(false);
                    update.setVisible(false);
                    update.setSelected(false);
                    add.setVisible(true);
                }
            }

        });
        buttons = new Buttons();
        formatedAdd();

        setVisible(true);
    }

    public void formatedAdd() {

        add(new RadioButtons(), BorderLayout.WEST);
        add(buttons, BorderLayout.SOUTH);
        JPanel north = new JPanel();
        north.setLayout(new BorderLayout());
        north.setOpaque(false);
        north.add(new TopBar(), BorderLayout.WEST);
        north.add(new SearchPanel(), BorderLayout.EAST);
        add(north, BorderLayout.NORTH);
    }

    /**
     * this inner class exists for formating purposes
     */
    private class RadioButtons extends JPanel {

        public RadioButtons() {
            setOpaque(false);
            add(student, BorderLayout.WEST);
            add(faculty, BorderLayout.EAST);
        }
    }

    /**
     * this inner class exists for formating purposes
     */
    private class Buttons extends JPanel {

        public Buttons() {
            setOpaque(false);
            add(update, BorderLayout.WEST);
            update.setVisible(false);
            add(add, BorderLayout.CENTER);
            update.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent ie) {
                    if (update.isSelected()) {
                        add.setVisible(false);
                        revalidate();
                        repaint();
                        add(edit, BorderLayout.CENTER);
                        if (index[0] == 0) {
                            faculty.setSelected(false);
                            student.setSelected(true);
                            studentsFormPanel.replaceFields((Students) studentList.get((int) index[1]));
                        } else {
                            student.setSelected(false);
                            faculty.setSelected(true);
                            facultyFormPanel.replaceFields((Faculty) facultyList.get((int) index[1]));
                        }
                    } else {
                        remove(edit);

                        add(add, BorderLayout.CENTER);
                        revalidate();
                        repaint();
                    }
                }
            });
        }

        public void addDelete() {
            add(delete, BorderLayout.EAST);
            delete.setVisible(true);
        }

        public void removeDelete() {
            remove(delete);
            revalidate();
            repaint();
        }
    }

    /**
     * this inner class exists for formating purposes also creates the search
     * utility in the frame
     */
    private class SearchPanel extends JPanel {

        private JTextArea area;

        public SearchPanel() {
            setLayout(new FlowLayout());
            setOpaque(false);
            area = new JTextArea("Search(ID)...", 1, 12);
            area.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent fe) {
                    area.selectAll();
                    update.setVisible(false);
                }

                @Override
                public void focusLost(FocusEvent fe) {
                    index = data.search(area.getText());
                    if (index == null) {
                        JOptionPane.showMessageDialog(null, "No such ID");
                        area.setText("Search(ID)...");
                        update.setVisible(false);
                        delete.setVisible(false);
                        return;
                    } else if (index[0] == 1) {
                        facultyTable.open();
                        facultyTable.select((int) (index[1]));
                        update.setVisible(true);
                        buttons.addDelete();
                        return;
                    } else {
                        studentTable.open();
                        studentTable.select((int) index[1]);
                        update.setVisible(true);
                        buttons.addDelete();
                        return;
                    }
                }
            });
            add(area);
            setVisible(true);
        }
    }

    /**
     * this inner class exists for formating purposes has the 3 graphic buttons
     * in it
     */
    private class TopBar extends JPanel {

        private JButton save;
        private JButton open;
        private JButton exit;
        private JFileChooser fileChooser;

        public TopBar() {
            setLayout(new BorderLayout());
            setOpaque(false);
            setSize(10, 20);
            save = new JButton(new ImageIcon("save-30.png"));
            save.setOpaque(false);
            open = new JButton(new ImageIcon("open-30.png"));
            open.setOpaque(false);
            exit = new JButton(new ImageIcon("exit-30.png"));
            exit.setOpaque(false);

            exit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int action = JOptionPane.showConfirmDialog(null, " Are you sure? ", "Exit", JOptionPane.OK_CANCEL_OPTION);
                    if (action == JOptionPane.OK_OPTION) {
                        System.exit(0);
                    }
                }
            });
            open.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    fileChooser = new JFileChooser();
                    fileChooser.addChoosableFileFilter(new BinaryFileFilter());
                    if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                        try {
                            File file = fileChooser.getSelectedFile();
                            FileInputStream fis = new FileInputStream(file);
                            ObjectInputStream ois = new ObjectInputStream(fis);

                            try {
                                data.clear();
                                data = (Database) ois.readObject();
                                studentList = data.getStudentList();
                                facultyList = data.getFacultyList();
                                facultyTable.setData(facultyList);
                                studentTable.setData(studentList);

                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            ois.close();
                            studentTable.refresh();
                            facultyTable.refresh();
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(MainFrame.this,
                                    "Failed loading data.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });

            save.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    fileChooser = new JFileChooser();
                    fileChooser.addChoosableFileFilter(new BinaryFileFilter());
                    if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                        try {
                            File file = fileChooser.getSelectedFile();
                            FileOutputStream fos = new FileOutputStream(file);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);

                            oos.writeObject(data);
                            oos.close();
                            System.out.println(data.toString());
                            System.out.println("FUCL");
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(MainFrame.this,
                                    "Failed saving data.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });

            add(save, BorderLayout.WEST);
            add(open, BorderLayout.CENTER);
            add(exit, BorderLayout.EAST);

        }
    }

    /**
     * this inner class creates the menu bar and all of the items in it
     */
    private class MenuBar extends JMenuBar {

        private DatabaseMySQL sqlData;
        private JMenuBar menuBar;
        private JMenu sql;
        private JMenu fileMenu;
        private JMenu viewMenu;
        private JMenuItem prefsItem;
        private JMenuItem exitItem;
        private JMenuItem openItem;
        private JMenuItem saveItem;
        private JMenuItem sqlLoadFromFile;
        private JMenuItem sqlSaveToFile;
        private JMenuItem mysqlsaveItem;
        private JFileChooser fileChooser;
        private Preferences prefs;
        private JMenu windowMenu = new JMenu("Window");

        public MenuBar() {
        }

        public JMenuBar buildMenuBar() {
            menuBar = new JMenuBar();
            buildFileMenu();
            buildViewMenu();
            menuBar.add(fileMenu);
            menuBar.add(viewMenu);
            menuBar.add(windowMenu);
            return menuBar;
        }

        private void buildFileMenu() {
            exitItem = new JMenuItem("Exit");
            exitItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int action = JOptionPane.showConfirmDialog(null, " Are you sure? ", "Exit", JOptionPane.OK_CANCEL_OPTION);
                    if (action == JOptionPane.OK_OPTION) {
                        System.exit(0);
                    }
                }
            });
            saveItem = new JMenuItem("Save As...");
            saveItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    fileChooser = new JFileChooser();
                    fileChooser.addChoosableFileFilter(new BinaryFileFilter());
                    if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                        try {
                            File file = fileChooser.getSelectedFile();
                            FileOutputStream fos = new FileOutputStream(file);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);

                            oos.writeObject(data);
                            oos.close();                           
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(MainFrame.this,
                                    "Failed saving data.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });
            openItem = new JMenuItem("Open...");
            openItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    fileChooser = new JFileChooser();
                    fileChooser.addChoosableFileFilter(new BinaryFileFilter());
                    if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                        try {
                            File file = fileChooser.getSelectedFile();
                            FileInputStream fis = new FileInputStream(file);
                            ObjectInputStream ois = new ObjectInputStream(fis);

                            try {
                                data.clear();
                                data = (Database) ois.readObject();
                                studentList = data.getStudentList();
                                facultyList = data.getFacultyList();
                                facultyTable.setData(facultyList);
                                studentTable.setData(studentList);

                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            ois.close();
                            studentTable.refresh();
                            facultyTable.refresh();
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(MainFrame.this,
                                    "Failed loading data.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });
            prefsItem = new JMenuItem("Preferences...");

            prefsItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    prefsDialog.setVisible(true);
                }

            });

            prefs = Preferences.userRoot().node("db");

            prefsDialog.setPrefsListener(new PrefsListener() {
                @Override
                public void preferencesSet(String user, String password, int port) {
                    prefs.put("user", user);
                    prefs.put("password", password);
                    prefs.putInt("port", port);
                }
            });

            String user = prefs.get("user", "");
            String password = prefs.get("password", "");
            Integer port = prefs.getInt("port", 3306);

            prefsDialog.setDefaults(user, password, port);

            sql = new JMenu("MySQL");

            mysqlsaveItem = new JMenuItem("Save to MySQL");
            mysqlsaveItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    sqlData = new DatabaseMySQL(data);
                    try {
                        sqlData.connect();
                    } catch (Exception ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        sqlData.save();
                    } catch (SQLException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
            sqlSaveToFile = new JMenuItem("Save to MySQL to file");
            sqlSaveToFile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    try {
                        fileChooser = new JFileChooser();
                        File file = fileChooser.getSelectedFile();
                        sqlData.saveToFile(file);
                    } catch (IOException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
            sqlLoadFromFile = new JMenuItem("Load MySQL from file");
            sqlLoadFromFile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    try {
                        fileChooser = new JFileChooser();
                        File file = fileChooser.getSelectedFile();
                        sqlData.saveToFile(file);
                    } catch (IOException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });

            saveItem.setMnemonic(KeyEvent.VK_S);
            saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));// makes it so that ctrl+s will open the save menu     
            openItem.setMnemonic(KeyEvent.VK_O);
            openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));// makes it so that ctrl+o will open the open menu     
            exitItem.setMnemonic(KeyEvent.VK_X);
            exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));// makes it so that ctrl+x will exit the gui  
            prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
            windowMenu.add(prefsItem);
            sql.add(mysqlsaveItem);
            sql.add(sqlSaveToFile);
            sql.add(sqlLoadFromFile);
            fileMenu = new JMenu("File");
            fileMenu.setMnemonic(KeyEvent.VK_F);// makes it so can alt+f to open menu
            fileMenu.add(openItem);
            fileMenu.add(saveItem);
            fileMenu.add(sql);
            fileMenu.add(exitItem);

        }

        private void buildViewMenu() {
            studentTable.setData(studentList);

            studentTable.setTableListener(new TableListener() {
                public void rowDeleted(int row) {
                    studentList.remove(row);
                    studentTable.refresh();
                }
            });
            facultyTable.setData(facultyList);
            facultyTable.setTableListener(new TableListener() {
                public void rowDeleted(int row) {
                    facultyList.remove(row);
                    facultyTable.refresh();
                }
            });

            studentTableItem = new JCheckBoxMenuItem("Student Table");
            studentTableItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (studentTableItem.getState()) {
                        studentTable.open();
                    } else {
                        studentTable.close();
                    }
                }
            });
            facultyTableItem = new JCheckBoxMenuItem("Faculty Table");
            facultyTableItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (facultyTableItem.getState()) {
                        facultyTable.open();
                    } else {
                        facultyTable.close();
                    }
                }
            });
            viewMenu = new JMenu("View");
            viewMenu.setMnemonic(KeyEvent.VK_V);
            viewMenu.add(studentTableItem);
            viewMenu.add(facultyTableItem);
        }
    }
}
