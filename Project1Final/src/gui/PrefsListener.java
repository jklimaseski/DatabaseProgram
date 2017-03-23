package gui;

/**
 * PrefsListener. this is a listener for the preferences created in the
 * prefsdialog class
 *
 */

public interface PrefsListener {

    public void preferencesSet(String user, String password, int port);
}
