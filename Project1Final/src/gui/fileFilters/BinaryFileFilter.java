package gui.fileFilters;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * BinaryFileFilter. a file filter for the binary file type allowing the read
 * write of binary file types
 *
 */

public class BinaryFileFilter extends FileFilter {

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }

        String fileName = file.getName();

        String extension = Utils.getFileExtension(fileName);

        if (extension == null) {
            return false;
        }

        if (extension.equals("bin")) {
            return true;
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "Binary File (*.bin)";
    }
}
