
import gui.MainFrame;
import javax.swing.SwingUtilities;

/**
 * DriverClass. This is the driver class used to run the program
 *
 * @author Jason Klimaseski
 */
public class Driver {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}
