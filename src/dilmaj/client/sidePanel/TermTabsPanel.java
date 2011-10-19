package dilmaj.client.sidePanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;

import dilmaj.client.DilmajConstants;
import dilmaj.client.login.LoginController;
import dilmaj.client.login.LoginListener;
import dilmaj.client.view_my_terms.MyTermsPanel;
import dilmaj.client.welcome.AllTermsPanel;

public class TermTabsPanel extends SidePanel implements LoginListener {
	private static TermTabsPanel theInstance=null;
	private DilmajConstants constants = GWT.create(DilmajConstants.class);

	public static TermTabsPanel getInstance() {
		if (theInstance==null)
			theInstance=new TermTabsPanel();
		
		return theInstance;
	}
	
	private TermTabsPanel() {
		super();
		
		LoginController.getInstance().addLoginListener(this);
		//addStyleName("sidePanel");
		
		add(constants.allTerms(), AllTermsPanel.getInstance());
		add(constants.suggestionTerms(), TermSuggestionsPanel.getInstance());
		
		select(constants.suggestionTerms());
	}

	@Override
	public void onLogin() {
		// TODO Auto-generated method stub
		add(constants.myTerms(), MyTermsPanel.getInstance());
		
		select(constants.myTerms());
	}

	@Override
	public void onLogout() {
		// TODO Auto-generated method stub
		remove(constants.myTerms());
		
		select(constants.allTerms());
	}
}
