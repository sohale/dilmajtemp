package dilmaj.client;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class SimpleEntryPanel extends HorizontalPanel {
	private TextBox textBox;
	
	@SuppressWarnings("unused") // textLabel is just added but never used
	private Label textLabel;
	
	public SimpleEntryPanel(TextBox textBox, Label textLabel) {
		this.textBox=textBox;
		this.textLabel=textLabel;
		
		add(textLabel);
		add(textBox);
	}
	
	public String getValue() {
		return textBox.getText();
	}
}
