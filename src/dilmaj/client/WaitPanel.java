package dilmaj.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Label;

import dilmaj.shared.GlobalSettings;

public class WaitPanel extends PopupPanel {
	static WaitPanel theInstance=null;
	private DilmajConstants constants = GWT.create(DilmajConstants.class);

	public static WaitPanel getInstance() {
		if (theInstance==null)
			theInstance=new WaitPanel();
		
		return theInstance;
	}
	
	private WaitPanel() {
		setGlassEnabled(true);
		setPopupPosition(0,0);
		Label label=new Label(constants.waitMessage());
		label.setStyleName("transparent");
		add(label);
	}
}
