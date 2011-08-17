package dilmaj.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;

import dilmaj.client.view_my_terms.MyTermsPanel;
import dilmaj.client.welcome.AllTermsPanel;
import dilmaj.client.welcome.TermSuggestionsPanel;

public class TermTabsPanel extends TabPanel {
	private static TermTabsPanel theInstance=null;

	public static TermTabsPanel getInstance() {
		if (theInstance==null)
			theInstance=new TermTabsPanel();
		
		return theInstance;
	}
	
	private TermTabsPanel() {
		super();
		
		add(AllTermsPanel.getInstance(), "All Terms");
		add(TermSuggestionsPanel.getInstance(), "Terms and Suggestions");
		add(MyTermsPanel.getInstance(), "My Terms");
	}

}
