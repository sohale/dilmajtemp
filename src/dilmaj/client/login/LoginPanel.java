package dilmaj.client.login;

import dilmaj.shared.GlobalSettings;
import dilmaj.shared.MessageComposite;

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
	private LoginController controller;
	private boolean isLogged=false;
	
	private static LoginPanel theInstance=null;
	
	public static LoginPanel getInstance() {
		if (theInstance==null)
			theInstance=new LoginPanel();
		
		return theInstance;
	}
	
	private LoginPanel() {
		add(username);
		add(password);
		
		add(buttonsPanel);
		
		controller=LoginController.getInstance();

		buttonsPanel.add(closeButton);
		buttonsPanel.add(loginButton);
		loginButton.addClickHandler(controller);
		closeButton.addClickHandler(controller);
		
		controller.checkLogin();
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
			//TopMenu.getInstance().login(username);
		}
	}
	
	public void logout() {
		if (isLogged) {
			username.setVisible(true);
			username.setText("");
			password.setVisible(true);
			loginButton.setText(GlobalSettings.constants.login());
			isLogged=false;
			//TopMenu.getInstance().logout();
		}
	}
}
