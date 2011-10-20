package dilmaj.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Label;

public class WaitPanel extends PopupPanel {
	static WaitPanel theInstance=null;
	private DilmajConstants constants = GWT.create(DilmajConstants.class);

	public static WaitPanel getInstance() {
		if (theInstance==null)
			theInstance=new WaitPanel();
		
		return theInstance;
	}
	
	private WaitPanel() {
		Label label=new Label(constants.waitMessage());
		add(label);
	}
}
