package dilmaj.client.welcome;

import java.util.HashMap;

import dilmaj.shared.TermComposite;

public enum AllTerms {
	TheInstance();
	
	HashMap<Long, TermComposite> allTerms;
	boolean isLoaded=false;
	AllTermsPanel allPanel=null;
	
	public boolean isLoaded() {
		return isLoaded;
	}
	
	private AllTerms() {
		
	}
	
	public void setAllTermsPanel(AllTermsPanel allTermsPanel) {
		allPanel=allTermsPanel;
	}
	
	public void setTerms(HashMap<Long, TermComposite> terms) {
		allTerms=terms;
		isLoaded=true;
		if (allPanel!=null)
			allPanel.populateTable();
	}

	public TermComposite add(TermComposite termVO) {
		allTerms.put(termVO.getId(), termVO);
		if (allPanel!=null)
			allPanel.updateTermsTable(termVO);
		return termVO;
	}
	
	public HashMap<Long, TermComposite> getTerms() {
		// TODO Auto-generated method stub
		return allTerms;
	}
}
