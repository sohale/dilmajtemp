package dilmaj.client.view_my_terms;

import java.util.HashMap;

import dilmaj.shared.TermComposite;

public enum MyTerms {
	TheInstance();
	
	HashMap<Long, TermComposite> myTerms;
	
	private MyTerms() {
		
	}
	
	public void setTerms(HashMap<Long, TermComposite> terms) {
		myTerms=terms;
	}
}
