package dilmaj.client.top;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

import dilmaj.client.DilmajUserService;
import dilmaj.client.DilmajUserServiceAsync;
import dilmaj.client.login.LoginPanel;
import dilmaj.client.settings.SettingsPanel;
import dilmaj.shared.Controller;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.MemberComposite;

public class TopMenuController extends Controller {
	private DilmajUserServiceAsync accountSvc = GWT.create(DilmajUserService.class);

	public TopMenuController() {
	}

	public void checkLogin() {
		accountSvc.getLoggedUser(new GetLoggedAccountCallback());
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onMouseOver(MouseOverEvent event) {
		// TODO Auto-generated method stub
		Label label=(Label)event.getSource();
		String caption=label.getText(); //is it logged in or out

		if (caption.compareTo(GlobalSettings.constants.settings())==0) {
			MenuPopup.getLoginPopup().hide();
			MenuPopup popup=MenuPopup.getSettingsPopup(); //for putting Login panel in it
/*			popup.hide();
			LoginPanel.getInstance().setVisible(false);
			popup.clear();

			if (settingsPanel==null)
				settingsPanel=SettingsPanel.getInstance(); //Pattern: FactoryMethod
			popup.add(settingsPanel);*/

			int left=label.getAbsoluteLeft()-100;
			//Window.alert(popup.getOffsetWidth()+"");
			int bottom=label.getAbsoluteTop()+label.getOffsetHeight();
			popup.setPopupPosition(left, bottom);

			popup.show();
		} else { //if (caption.compareTo(GlobalSettings.constants.login())==0) {
			MenuPopup.getSettingsPopup().hide();
			MenuPopup popup=MenuPopup.getLoginPopup(); //for putting Login panel in it
			/*popup.hide();
			SettingsPanel.getInstance().setVisible(false);
			popup.clear();
//			popup.setSize("100px", "100px");

			if (loginPanel==null)
				loginPanel=LoginPanel.getInstance(); //Pattern: FactoryMethod
			popup.add(loginPanel);*/

			int left=label.getAbsoluteLeft()-100;
			int bottom=label.getAbsoluteTop()+label.getOffsetHeight();
			popup.setPopupPosition(left, bottom);

			popup.show();
		}

	}

	@Override
	public void onMouseOut(MouseOutEvent event) {
		Label label=(Label)event.getSource();
		String caption=label.getTitle(); //is it logged in or out

		if (caption.compareTo(GlobalSettings.constants.settings())==0) {

		} else {
			LoginPanel.getInstance().getParent().setVisible(false);
		}
	}
}
