package dilmaj.client.top;

import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TabPanel;

import dilmaj.client.login.LoginPanel;
import dilmaj.client.register.RegisterPanel;
import dilmaj.client.settings.SettingsPanel;
import dilmaj.shared.GlobalSettings;

public class TopMenuTabs extends TabPanel {
	private static TopMenuTabs theInstance=null;
	private TopMenuController controller=new TopMenuController();
	//private Tab loginTab;//=new Tab(GlobalSettings.constants.login());
	//private TabBar tabBar=new TabBar();

	public static TopMenuTabs getInstance() {
		if (theInstance==null)
			theInstance=new TopMenuTabs();
		
		return theInstance;
	}
	
	private TopMenuTabs() {
		super();
		
		add(LoginPanel.getInstance(), GlobalSettings.constants.login());
		add(SettingsPanel.getInstance(), GlobalSettings.constants.settings());
		add(RegisterPanel.getInstance(), "Register");
		
		//controller.checkLogin();
	}
	
	public void login(String usename) {
		//tabBar.setTabText(0, usename);
	}
	
	public void logout() {
		//tabBar.setTabText(0, GlobalSettings.constants.login());
	}
}
