package gui;

import java.util.EventListener;

/**
 * TableListener. This class is a listener class used to carry the data from
 * mainframe to the tables
 *
 * @author Jason Klimaseski
 */
public interface TableListener extends EventListener {

    public void rowDeleted(int row);
}
