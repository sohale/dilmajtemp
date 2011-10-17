package dilmaj.client.sidePanel;

import com.google.gwt.user.client.ui.FlexTable;

public enum TermsTable {
	TermSuggestionTable() {
	},
	
	TermOnlyTable() {
		
	},
	
	MyTermsTable() {
		
	};
	
	private FlexTable theTable=new FlexTable();
	private boolean isLoaded=false;
	private SidePanelController controller=new SidePanelController();
	private int from, to;
	private HashMap<Long, TermComposite> loadedTerms=new HashMap<Long, TermComposite>();
	
	private TermsTable() {
		controller.loadMe(this, from, to);
	}
	
	public void populate(HashMap)
}
