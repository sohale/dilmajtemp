package dilmaj.client.top;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

import dilmaj.client.DilmajUserService;
import dilmaj.client.DilmajUserServiceAsync;
import dilmaj.client.MyPanel;
import dilmaj.client.login.LoginPanel;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.MessageComposite;

public class TopMenu extends HorizontalPanel implements MyPanel {
	private MessageComposite message;
	private FocusPanel loginPanel=new FocusPanel();
	private TopMenuController controller=new TopMenuController(this);
	Label loginLabel=new Label(GlobalSettings.constants.login());
	private HandlerRegistration mouseOver;
	
	public TopMenu() {
		loginPanel.setTitle(GlobalSettings.constants.login());
		loginPanel.add(loginLabel);
		add(loginPanel);
		mouseOver=loginPanel.addMouseOverHandler(controller);
		controller.checkLogin();
	}
	
	@Override
	public void setMessage(MessageComposite messageVO) {
		// TODO Auto-generated method stub
		message=messageVO;
		Window.alert(message.toString());
	}

	public void login(String username) {
		// TODO Auto-generated method stub
		loginLabel.setText(username);
		loginPanel.setTitle(username);
		LoginPanel.getInstance(this).login(username);
		//mouseOver.removeHandler();
	}
	
	public void logout() {
		loginPanel.setTitle(GlobalSettings.constants.login());
		loginLabel.setText(GlobalSettings.constants.login());
		LoginPanel.getInstance(this).logout();
	}
}
