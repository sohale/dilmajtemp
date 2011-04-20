package dilmaj.client.welcome;

import java.util.HashMap;

import dilmaj.shared.TermComposite;

public enum AllTerms {
	TheInstance();
	
	HashMap<Long, TermComposite> allTerms;
	
	private AllTerms() {
		
	}
	
	public void setTerms(HashMap<Long, TermComposite> terms) {
		allTerms=terms;
	}

	public HashMap<Long, TermComposite> getTerms() {
		// TODO Auto-generated method stub
		return allTerms;
	}
}
