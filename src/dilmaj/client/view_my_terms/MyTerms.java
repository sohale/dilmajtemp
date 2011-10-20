package dilmaj.client.view_my_terms;

import java.util.HashMap;
import java.util.Iterator;

import dilmaj.client.sidePanel.MyTermsPanel;
import dilmaj.shared.TermComposite;

public enum MyTerms {
	TheInstance();
	
	private MyTermsPanel myPanel=null;
	private boolean isLoaded=false;
	HashMap<Long, TermComposite> myTerms=null;
	
	private MyTerms() {
		
	}
	
	public void clear() {
		if (myTerms!=null) {
			myTerms.clear();
			myTerms=null;
			//myPanel.populateTable();
		}
		isLoaded=false;
	}
	
	public void setMyPanel(MyTermsPanel myPanel) {
		this.myPanel=myPanel;
		if (myTerms!=null && !isLoaded) {
			//myPanel.populateTable();
			isLoaded=true;
		}
	}
	
	public void setTerms(HashMap<Long, TermComposite> terms) {
		myTerms=terms;
		
		if (myPanel!=null && !isLoaded) {
			//myPanel.populateTable();
			//myPanel.browseFirst();
			isLoaded=true;
		}
	}
	
	public void addTerm(TermComposite newTerm) {
		if (myTerms!=null)
			myTerms=new HashMap<Long, TermComposite>();
		myTerms.put(newTerm.getId(), newTerm);
	}
	
	public HashMap<Long, TermComposite> getTerms() {
		// TODO Auto-generated method stub
		return myTerms;
	}
}
