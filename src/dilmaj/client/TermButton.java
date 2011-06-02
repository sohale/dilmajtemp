package dilmaj.client;

import com.google.gwt.user.client.ui.Button;

import dilmaj.shared.TermComposite;

public class TermButton extends Button {
	TermComposite termComposite=null;
	
	public TermButton(String term) {
		super(term);
	}
	
	public TermButton(TermComposite term) {
		super(term.getCaption());
		termComposite=term;
	}
	
	public TermComposite getTermComposite() {
		return termComposite;
	}
}
