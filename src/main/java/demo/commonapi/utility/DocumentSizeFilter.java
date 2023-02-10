/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.commonapi.utility;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class DocumentSizeFilter extends DocumentFilter {
    private final int maxCharacters;
    private final String pattern;
    public DocumentSizeFilter(final int maxChars, final String pattern) {
        maxCharacters = maxChars;
        this.pattern = pattern;
    }
        public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a)
            throws BadLocationException {
        if (str.matches(pattern) && (fb.getDocument().getLength() + str.length() - length) <= maxCharacters) {
            super.replace(fb, offs, length, str, a);
        } else {
            Toolkit.getDefaultToolkit().beep(); 
        }
    }
}
