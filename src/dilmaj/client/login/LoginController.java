package dilmaj.client.login;

import java.util.HashSet;
import java.util.Set;

import dilmaj.client.DilmajUserService;
import dilmaj.client.DilmajUserServiceAsync;
import dilmaj.client.top.GetLoggedAccountCallback;
import dilmaj.client.top.MenuPopup;
import dilmaj.shared.Controller;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.MemberComposite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoginController extends Controller {
	private DilmajUserServiceAsync accountSvc = GWT.create(DilmajUserService.class);
	
	private Set<LoginListener> loginListeners=new HashSet<LoginListener>();
	
	private static LoginController theInstance=null;

	public static LoginController getInstance() {
		if (theInstance==null)
			theInstance=new LoginController();
		
		return theInstance;
	}
	
	private LoginController() {
	}
	
	public void addLoginListener(LoginListener loginListener) {
		loginListeners.add(loginListener);
	}
	
	public void login() {
		for (LoginListener loginListener:loginListeners)
			loginListener.onLogin();
	}
	
	public void logout() {
		for (LoginListener loginListener:loginListeners)
			loginListener.onLogout();
	}
	
	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		Button button=(Button)event.getSource();
		String sourceTitle=button.getText();

		LoginPanel panel=LoginPanel.getInstance();
		
		Widget parent=panel.getParent(); // there is a popup
		
		/*if (parent!=null) { //if popped out, hide it
			if (parent.getClass().equals(PopupPanel.class)) {
				PopupPanel popup=(PopupPanel)parent;
				popup.hide();
			}
		} else {
			panel.setVisible(false);
		}*/
		MenuPopup.getLoginPopup().hide();
		
		if (sourceTitle.compareTo(GlobalSettings.constants.login())==0) {
			MemberComposite accountVO=new MemberComposite(panel.getUsername(), panel.getPassword());
			accountSvc.find(accountVO, new FindCallback(panel));
		} else if (sourceTitle.compareTo(GlobalSettings.constants.logout())==0) {
			accountSvc.logout(new LogoutCallback(panel));
		}
	}

	public void checkLogin() {
		accountSvc.getLoggedUser(new GetLoggedAccountCallback());
	}
	
	@Override
	public void onMouseOut(MouseOutEvent event) {
		// TODO Auto-generated method stub
	}
}
