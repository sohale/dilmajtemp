package dilmaj.client.settings;

import dilmaj.client.top.TopMenu;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.MessageComposite;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SettingsPanel extends VerticalPanel {
	private Button closeButton=new Button("x");
	private SettingsController controller=new SettingsController(this);
	private TopMenu topMenu;
	private MessageComposite message;
	
	private static SettingsPanel theInstance=null;
	
	public static SettingsPanel getInstance(TopMenu topMenu) {
		if (theInstance==null)
			theInstance=new SettingsPanel(topMenu);
		
		return theInstance;
	}
	
	private SettingsPanel(TopMenu topMenu) {
		closeButton.addClickHandler(controller);
		add(closeButton);
		
		this.topMenu=topMenu;
	}
	
	public void setMessage(MessageComposite messageVO) {
		message=messageVO;
		Window.alert(message.toString());
	}
}
