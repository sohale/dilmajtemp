package dilmaj.client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import dilmaj.shared.GlobalSettings;

public class WaitPanel extends PopupPanel {
	static WaitPanel theInstance=null;
	private DilmajConstants constants = GWT.create(DilmajConstants.class);
	private HashMap<String, Label> messages=new HashMap<String, Label>();
	private VerticalPanel mainPanel=new VerticalPanel();
	
	public static WaitPanel getInstance() {
		if (theInstance==null)
			theInstance=new WaitPanel();
		
		return theInstance;
	}
	
	private WaitPanel() {
		setGlassEnabled(true);
		int x=GlobalSettings.getBrowserWidth()/6;
		int y=GlobalSettings.getBrowserHeight()/4;
		setPopupPosition(x,y);
		add(mainPanel);
		mainPanel.setStyleName("transparent");
		setStyleName("transparent");
	}
	
	public void addMessage(String message) {
		Label label=messages.get(message);
		if (label==null) {
			label=new Label(message);
			messages.put(message, label);
		}
		label.setStyleName("transparent");
		mainPanel.add(label);
		if (mainPanel.getWidgetCount()==1)
			show();
	}
	
	public void removeMessage(String message) {
		Label label=messages.get(message);
		if (label!=null) {
			label.setStyleName("transparentDone");
			messages.remove(message);
			if (messages.size()==0) {
				mainPanel.clear();
				hide();
			}
		}
	}
}
