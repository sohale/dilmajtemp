package dilmaj.client.top;

import dilmaj.client.top.TopMenu;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.MessageComposite;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EmptyPanel extends VerticalPanel {
	public EmptyPanel() {
		add(new Label("<--->"));
	}
}
