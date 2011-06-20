package dilmaj.client.settings;

import dilmaj.client.DilmajUserService;
import dilmaj.client.DilmajUserServiceAsync;
import dilmaj.client.welcome.TermSuggestionsPanel;
import dilmaj.shared.Controller;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.MemberComposite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class SettingsController extends Controller {
	private SettingsPanel panel;

	public SettingsController(SettingsPanel panel) {
		this.panel=panel;
	}
	
	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		Button button=(Button)event.getSource();
		String sourceTitle=button.getText();

		
		Widget parent=panel.getParent(); // there is a popup
		
		if (parent!=null) { //if popped out, hide it
			try {
				panel.update();
				PopupPanel popup=(PopupPanel)parent;
				popup.hide();
				TermSuggestionsPanel.getInstance().browseFirst();
			} catch (ClassCastException cce) {
			}
		} else {
			panel.setVisible(false);
		}
	}


	@Override
	public void onMouseOut(MouseOutEvent event) {
		// TODO Auto-generated method stub
	}
}
