package dilmaj.client.termPanel;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

import dilmaj.shared.TermComposite;

public class TermButton extends Label {
	TermComposite termComposite=null;
	
	public TermButton(String term) {
		super(term);
		setStyleName("termButton");
	}
	
	public TermButton(TermComposite term) {
		super(term.getCaption());
		termComposite=term;
		setStyleName("termButton");
	}
	
	public TermComposite getTermComposite() {
		return termComposite;
	}
	
	public void setNewStyle(String styleName) {
		setStyleName(styleName);
	}
}
