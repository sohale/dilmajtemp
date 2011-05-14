package dilmaj.client.login;

import dilmaj.client.DilmajUserService;
import dilmaj.client.DilmajUserServiceAsync;
import dilmaj.shared.Controller;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.UserComposite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoginController extends Controller {
	private DilmajUserServiceAsync accountSvc = GWT.create(DilmajUserService.class);
	private LoginPanel panel;

	public LoginController(LoginPanel panel) {
		this.panel=panel;
	}
	
	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		Button button=(Button)event.getSource();
		String sourceTitle=button.getText();

		
		Widget parent=panel.getParent(); // there is a popup
		
		if (parent!=null) { //if popped out, hide it
			try {
				PopupPanel popup=(PopupPanel)parent;
				popup.hide();
			} catch (ClassCastException cce) {
			}
		} else {
			panel.setVisible(false);
		}
		
		if (sourceTitle.compareTo(GlobalSettings.constants.login())==0) {
			MemberComposite accountVO=new MemberComposite(panel.getUsername(), panel.getPassword());
			accountSvc.find(accountVO, new FindCallback(panel));
		} else if (sourceTitle.compareTo(GlobalSettings.constants.logout())==0) {
			accountSvc.logout(new LogoutCallback(panel));
		}
	}


	@Override
	public void onMouseOut(MouseOutEvent event) {
		// TODO Auto-generated method stub
	}
}
