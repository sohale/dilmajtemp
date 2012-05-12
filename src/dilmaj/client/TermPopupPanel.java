package dilmaj.client;

import com.google.gwt.user.client.ui.PopupPanel;

// might be required in future to have only one popup created for all terms
public enum TermPopupPanel {
	TheInstance();
	
	PopupPanel popupPanel=null;
	
	public PopupPanel getInstance() {
		if (popupPanel==null)
			popupPanel=new PopupPanel();
		return popupPanel;
	}
}
