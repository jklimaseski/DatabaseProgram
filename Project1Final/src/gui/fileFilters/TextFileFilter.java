package gui.fileFilters;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * TextFileFilter. a file filter that i didn't end up adding but would allow to
 * read and write to .txt
 */
public class TextFileFilter extends FileFilter {

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

        if (extension.equals("txt")) {
            return true;
        }

        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public String getDescription() {
        return "Text File (*.txt)";
    }
}
