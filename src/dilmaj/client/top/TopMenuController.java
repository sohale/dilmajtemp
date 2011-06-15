package dilmaj.client.top;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

import dilmaj.client.DilmajUserService;
import dilmaj.client.DilmajUserServiceAsync;
import dilmaj.client.login.LoginPanel;
import dilmaj.client.settings.SettingsPanel;
import dilmaj.shared.Controller;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.MemberComposite;

public class TopMenuController extends Controller {
	private LoginPanel loginPanel=null;
	private SettingsPanel settingsPanel=null;
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
		FocusPanel panel=(FocusPanel)event.getSource();
		String caption=panel.getTitle(); //is it logged in or out
		
		if (caption.compareTo(GlobalSettings.constants.settings())==0) {
			PopupPanel popup=new PopupPanel(); //for putting Login panel in it
			
			popup.setSize("100px", "100px");
			
			int left=panel.getAbsoluteLeft();
			int bottom=panel.getAbsoluteTop()+panel.getOffsetHeight();
			popup.setPopupPosition(left, bottom);
			
			if (settingsPanel==null)
				settingsPanel=SettingsPanel.getInstance(); //Pattern: FactoryMethod
			popup.add(settingsPanel);
			
			popup.show();
		} else { //if (caption.compareTo(GlobalSettings.constants.login())==0) {
			PopupPanel popup=new PopupPanel(); //for putting Login panel in it
			
			popup.setSize("100px", "100px");
			
			int left=panel.getAbsoluteLeft();
			int bottom=panel.getAbsoluteTop()+panel.getOffsetHeight();
			popup.setPopupPosition(left, bottom);
			
			if (loginPanel==null)
				loginPanel=LoginPanel.getInstance(); //Pattern: FactoryMethod
			popup.add(loginPanel);
			
			popup.show();
		}
		
	}
}
