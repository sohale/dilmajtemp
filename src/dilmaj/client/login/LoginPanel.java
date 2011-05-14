package dilmaj.client.login;

import dilmaj.client.DilmajUserService;
import dilmaj.client.DilmajUserServiceAsync;
import dilmaj.client.top.TopMenu;
import dilmaj.client.view_my_terms.MyTermsController;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.MessageComposite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginPanel extends VerticalPanel {
	private TextBox username=new TextBox();
	private PasswordTextBox password=new PasswordTextBox();
	private Button loginButton=new Button(GlobalSettings.constants.login());
	private MessageComposite message;
	private Button closeButton=new Button("x");
	private HorizontalPanel buttonsPanel=new HorizontalPanel();
	private LoginController controller=new LoginController(this);
	private TopMenu topMenu;
	private boolean isLogged=false;
	
	private static LoginPanel theInstance=null;
	
	public static LoginPanel getInstance(TopMenu topMenu) {
		if (theInstance==null)
			theInstance=new LoginPanel(topMenu);
		
		return theInstance;
	}
	
	private LoginPanel(TopMenu topMenu) {
		add(username);
		add(password);
		
		add(buttonsPanel);

		buttonsPanel.add(closeButton);
		buttonsPanel.add(loginButton);
		loginButton.addClickHandler(controller);
		closeButton.addClickHandler(controller);
		
		this.topMenu=topMenu;
	}
	
	public void setMessage(MessageComposite messageVO) {
		message=messageVO;
		Window.alert(message.toString());
	}
	
	public String getUsername() {
		return username.getValue();
	}
	
	public String getPassword() {
		return password.getValue();
	}
	
	public void login(String username) {
		if (!isLogged) { //if not logged in by TopMenu
			this.username.setVisible(false);
			this.password.setText("");
			this.password.setVisible(false);
			loginButton.setText(GlobalSettings.constants.logout());
			isLogged=true;
			topMenu.login(username);
		}
	}
	
	public void logout() {
		if (isLogged) {
			username.setVisible(true);
			username.setText("");
			password.setVisible(true);
			loginButton.setText(GlobalSettings.constants.login());
			isLogged=false;
			topMenu.logout();
		}
	}
}
