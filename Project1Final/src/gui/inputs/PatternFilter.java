package gui.inputs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * PatternFilter. this class allowed me to set a filter on the inputs allowed
 * into the text areas of the other panels
 *
 */

public class PatternFilter extends DocumentFilter {

    private Pattern pattern;
    private int maxCharacters;

    public PatternFilter(String pat, int pat2) {
        pattern = Pattern.compile(pat);
        maxCharacters = pat2;
    }

    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {

        String newStr = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
        Matcher m = pattern.matcher(newStr);
        if (m.matches() && ((fb.getDocument().getLength() + string.length()) <= maxCharacters)) {
            super.insertString(fb, offset, string, attr);
        } else {
        }
    }

    public void replace(FilterBypass fb, int offset,
            int length, String string, AttributeSet attr) throws
            BadLocationException {

        if (length > 0) {
            fb.remove(offset, length);
        }
        insertString(fb, offset, string, attr);
    }
}
