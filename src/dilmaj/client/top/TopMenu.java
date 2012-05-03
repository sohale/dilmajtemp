package dilmaj.client.top;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import dilmaj.client.MyPanel;
import dilmaj.client.login.LoginPanel;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.MessageComposite;

public class TopMenu {//extends HorizontalPanel implements MyPanel {
	private MessageComposite message;

	private FocusPanel loginPanel=new FocusPanel();
	private FocusPanel settingsPanel=new FocusPanel();
	
	private TopMenuController controller=new TopMenuController();
	Label loginLabel=new Label(GlobalSettings.constants.login());
	Label settingsLabel=new Label(GlobalSettings.constants.settings());
	//private HandlerRegistration mouseOver;
	
	private static TopMenu topMenu=null;
	
	public static TopMenu getInstance() {
		if (topMenu==null)
			topMenu=new TopMenu();
		
		return topMenu;
	}
	
	private TopMenu() {
		//setStyleName("navigation");
		
		/*loginPanel.setTitle(GlobalSettings.constants.login());
		loginPanel.add(loginLabel);
		add(loginPanel);
		loginPanel.addMouseOverHandler(controller);
		
		add(new EmptyPanel());
		
		settingsPanel.setTitle(GlobalSettings.constants.settings());
		settingsPanel.add(settingsLabel);
		add(settingsPanel);
		settingsPanel.addMouseOverHandler(controller);*/
		RootPanel.get("navigation").setStyleName("navigation");
		loginLabel.setStyleName("navigationLabel");
		RootPanel.get("navigation").add(loginLabel);
		settingsLabel.setStyleName("navigationLabel");
		RootPanel.get("navigation").add(settingsLabel);
		loginLabel.addMouseOverHandler(controller);
		settingsLabel.addMouseOverHandler(controller);
		//loginLabel.addMouseOutHandler(controller);
		
		controller.checkLogin();
	}
	
	public void setMessage(MessageComposite messageVO) {
		// TODO Auto-generated method stub
		message=messageVO;
		Window.alert(message.toString());
	}

	public void login(String username) {
		// TODO Auto-generated method stub
		loginLabel.setText(username);
		loginPanel.setTitle(username);
		LoginPanel.getInstance().login(username);
		//mouseOver.removeHandler();
	}
	
	public void logout() {
		loginPanel.setTitle(GlobalSettings.constants.login());
		loginLabel.setText(GlobalSettings.constants.login());
		LoginPanel.getInstance().logout();
	}
}
