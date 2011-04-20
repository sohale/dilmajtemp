package dilmaj.client.register;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dilmaj.client.SimpleEntryPanel;
import dilmaj.shared.MessageComposite;

public class RegisterPanel extends VerticalPanel {
	private MessageComposite message;
	
	RegisterController controller=new RegisterController(this);

	TextBox usernameBox=new TextBox();
	PasswordTextBox passwordBox=new PasswordTextBox();
	PasswordTextBox re_passwordBox=new PasswordTextBox();
	TextBox emailBox=new TextBox();
	TextBox re_emailBox=new TextBox();
	TextBox nameBox=new TextBox();
	Button registerButton=new Button("Register");
	PasswordTextBox adminPinBox=new PasswordTextBox();
	PasswordTextBox re_adminPinBox=new PasswordTextBox();
	
	Label usernameLabel=new Label("* Username");
	Label passwordLabel=new Label("* Password");
	Label re_passwordLabel=new Label("* Re-Password");
	Label emailLabel=new Label("* Email");
	Label re_emailLabel=new Label("* Re-Email");
	Label nameLabel=new Label("Name");
	Label adminPinLabel=new Label("Admin Pin");
	Label re_adminPinLabel=new Label("Re-Admin Pin");

	SimpleEntryPanel usernamePanel=new SimpleEntryPanel(usernameBox, usernameLabel);
	SimpleEntryPanel passwordPanel=new SimpleEntryPanel(passwordBox, passwordLabel);
	SimpleEntryPanel re_passwordPanel=new SimpleEntryPanel(re_passwordBox, re_passwordLabel);
	SimpleEntryPanel emailPanel=new SimpleEntryPanel(emailBox, emailLabel);
	SimpleEntryPanel re_emailPanel=new SimpleEntryPanel(re_emailBox, re_emailLabel);
	SimpleEntryPanel re_adminPinPanel=new SimpleEntryPanel(re_adminPinBox, re_adminPinLabel);
	SimpleEntryPanel adminPinPanel=new SimpleEntryPanel(adminPinBox, adminPinLabel);
	SimpleEntryPanel namePanel=new SimpleEntryPanel(nameBox, nameLabel);

	public RegisterPanel() {
		super();

		add(usernamePanel);
		add(passwordPanel);
		add(re_passwordPanel);
		add(emailPanel);
		add(re_emailPanel);
		add(namePanel);
		add(adminPinPanel);
		add(re_adminPinPanel);
		add(registerButton);
		
		registerButton.addClickHandler(controller);
	}
	
	public String getUsername() {
		return usernamePanel.getValue();
	}
	
	public String getPassword() {
		return passwordPanel.getValue();
	}
	
	public String getAdminPin() {
		return adminPinPanel.getValue();
	}
	
	public String getEmail() {
		return emailPanel.getValue();
	}
	
	public String getRePassword() {
		return passwordPanel.getValue();
	}
	
	public String getReAdminPin() {
		return adminPinPanel.getValue();
	}
	
	public String getReEmail() {
		return emailPanel.getValue();
	}
	
	public String getNewName() {
		return namePanel.getValue();
	}
	
	public void setMessage(MessageComposite messageVO) {
		message=messageVO;
		Window.alert(message.toString());
	}
	
	public RegisterController getController() {
		return controller;
	}
}
