package dilmaj.client.settings;

import dilmaj.client.top.TopMenu;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.SettingsComposite;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SettingsPanel extends VerticalPanel {
	private Button closeButton=new Button("x");
	private SettingsController controller=new SettingsController(this);
	private MessageComposite message;
	private SettingsComposite settingsVO;
	private Label label;
	
	private Label termsLabel;
	private TextBox termsBox;
	
	private static SettingsPanel theInstance=null;
	
	public static SettingsPanel getInstance() {
		if (theInstance==null)
			theInstance=new SettingsPanel();
		
		return theInstance;
	}
	
	private SettingsPanel() {
		closeButton.addClickHandler(controller);
		add(closeButton);
		label=new Label();
		add(label);
		
		termsLabel=new Label("Number of terms in each page");
		add(termsLabel);
		termsBox=new TextBox();
		add(termsBox);
	}
	
	public void setMessage(MessageComposite messageVO) {
		message=messageVO;
		Window.alert(message.toString());
	}
	
	public void setSettings(SettingsComposite settings) {
		settingsVO=settings;
		if (settings!=null) {
			//label.setText(settings.getId()+"");
			termsBox.setText(settings.getTermsPerPage()+128+"");
		} else {
			//label.setText("");
			termsBox.setText("");
		}
	}
	
	public SettingsComposite getSettings() {
		return settingsVO;
	}
}
