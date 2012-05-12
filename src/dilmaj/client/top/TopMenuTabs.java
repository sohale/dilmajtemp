package dilmaj.client.top;

import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TabPanel;

import dilmaj.client.login.LoginController;
import dilmaj.client.login.LoginListener;
import dilmaj.client.login.LoginPanel;
import dilmaj.client.register.RegisterPanel;
import dilmaj.client.settings.SettingsPanel;
import dilmaj.shared.GlobalSettings;

public class TopMenuTabs extends TabPanel implements LoginListener {
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

		LoginController.getInstance().addLoginListener(this);
		
		add(LoginPanel.getInstance(), GlobalSettings.constants.login());
		add(SettingsPanel.getInstance(), GlobalSettings.constants.settings());
		add(RegisterPanel.getInstance(), "Register");
		
		//controller.checkLogin();
	}
	
	public void login(String username) {
		insert(LoginPanel.getInstance(), username, 1);
		//remove(0);
		//tabBar.setTabText(0, usename);
	}

	@Override
	public void onLogin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLogout() {
		// TODO Auto-generated method stub
		insert(LoginPanel.getInstance(), GlobalSettings.constants.login(), 1);
	}
}
