/**
 * 
 */
package org.jkiss.dbeaver.ui.editors.sql.syntax;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.PatternRule;

/**
 * PostgreSQL dollar quoted string literal rule
 * 
 * Loosely based on WordPatternRule
 */
public class DollarQuoteRule extends PatternRule { // implements IPredicateRule
    /** The internal buffer used for pattern detection */
	private StringBuffer fBuffer= new StringBuffer();

	/**
	 * 
	 */
	public DollarQuoteRule(IToken token) {
		super("$", "$", token, (char)0, false);
	}

	/**
	 * Returns whether the end sequence was detected.
	 * The rule acquires the rest of the word, using the
	 * provided word detector, and tests to determine if
	 * it ends with the end sequence.
	 *
	 * @param scanner the scanner to be used
	 * @return <code>true</code> if the word ends on the given end sequence
	 */
	@Override
	protected boolean endSequenceDetected(ICharacterScanner scanner) {
		if (1 == fStartSequence.length) {
			fBuffer.setLength(0);
			int c= scanner.read();
			int cnt= 0;
			while (Character.isUnicodeIdentifierStart(c)) {
				fBuffer.append((char) c);
				c= scanner.read();
				cnt++;
			}
			if ('$' == c) {
				// TODO: Do something about hardcoded value
				if (fBuffer.toString().equals("function")) {
					while(cnt-->0)
						scanner.unread();
					return false;
				}
				fEndSequence = fStartSequence = new char[cnt+2];
				fEndSequence[0] = fEndSequence[cnt+1] = '$';
				fBuffer.getChars(0, cnt, fEndSequence, 1);
			} else {
				scanner.unread();
				return false;
			}
		}
		return super.endSequenceDetected(scanner);
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner, boolean resume) {
		fEndSequence = fStartSequence = new char[] {'$'};
		return super.evaluate(scanner, resume);
	}
}
