package dilmaj.client.top;

import com.google.gwt.user.client.ui.PopupPanel;

import dilmaj.client.login.LoginPanel;
import dilmaj.client.settings.SettingsPanel;

public class MenuPopup extends PopupPanel {
	private static MenuPopup loginPopup=null;
	private static MenuPopup settingsPopup=null;
	
	public static MenuPopup getLoginPopup() {
		if (loginPopup==null) {
			loginPopup=new MenuPopup();
			loginPopup.add(LoginPanel.getInstance());
		}
		
		return loginPopup;
	}
	
	public static MenuPopup getSettingsPopup() {
		if (settingsPopup==null) {
			settingsPopup=new MenuPopup();
			settingsPopup.add(SettingsPanel.getInstance());
		}
		
		return settingsPopup;
	}
	
	private MenuPopup() {
		setSize("100px", "100px");
	}
}
