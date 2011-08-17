package dilmaj.client.top;

import com.google.gwt.user.client.ui.TabPanel;

import dilmaj.client.TermTabsPanel;
import dilmaj.client.login.LoginPanel;
import dilmaj.client.register.RegisterPanel;
import dilmaj.client.settings.SettingsPanel;
import dilmaj.client.view_my_terms.MyTermsPanel;
import dilmaj.client.welcome.AllTermsPanel;
import dilmaj.client.welcome.TermSuggestionsPanel;

public class TopMenuTabs extends TabPanel {
	private static TopMenuTabs theInstance=null;

	public static TopMenuTabs getInstance() {
		if (theInstance==null)
			theInstance=new TopMenuTabs();
		
		return theInstance;
	}
	
	private TopMenuTabs() {
		super();
		
		add(LoginPanel.getInstance(), "Login");
		add(SettingsPanel.getInstance(), "Settings");
		add(RegisterPanel.getInstance(), "Register");
	}
}
