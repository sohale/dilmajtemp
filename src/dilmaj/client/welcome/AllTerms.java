package dilmaj.client.welcome;

import java.util.HashMap;
import java.util.Iterator;

import dilmaj.client.sidePanel.AllTermsPanel;
import dilmaj.shared.TermComposite;

public enum AllTerms {
	TheInstance();
	
	HashMap<Long, TermComposite> allTerms=null;
	boolean isLoaded=false;
	AllTermsPanel allPanel=null;
	
	public boolean isLoaded() {
		return isLoaded;
	}
	
	private AllTerms() {
		
	}
	
	public void setAllTermsPanel(AllTermsPanel allTermsPanel) {
		allPanel=allTermsPanel;
		if (!isLoaded && allTerms!=null) {
			//allPanel.populateTable();
			isLoaded=true;
		}
	}
	
	public void setTerms(HashMap<Long, TermComposite> terms) {
		allTerms=terms;
		
		if (allPanel!=null && !isLoaded) {
			//allPanel.populateTable();
			isLoaded=true;
		}
	}

	public TermComposite add(TermComposite termVO) {
		allTerms.put(termVO.getId(), termVO);
/*		if (allPanel!=null)
			allPanel.updateTermsTable(termVO);*/
		return termVO;
	}
	
	public HashMap<Long, TermComposite> getTerms() {
		// TODO Auto-generated method stub
		return allTerms;
	}
}
