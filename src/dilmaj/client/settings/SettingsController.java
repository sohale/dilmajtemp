package dilmaj.client.settings;

import dilmaj.client.DilmajConstants;
import dilmaj.client.DilmajUserService;
import dilmaj.client.DilmajUserServiceAsync;
import dilmaj.client.SettingsService;
import dilmaj.client.SettingsServiceAsync;
import dilmaj.client.TermSuggestionService;
import dilmaj.client.TermSuggestionServiceAsync;
import dilmaj.client.sidePanel.AllTermsPanel;
import dilmaj.client.sidePanel.TermSuggestionsPanel;
import dilmaj.client.top.MenuPopup;
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
	private DilmajConstants constants = GWT.create(DilmajConstants.class);
	private SettingsServiceAsync settingsSvc = GWT.create(SettingsService.class);

	public SettingsController(SettingsPanel panel) {
		this.panel=panel;
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		Button button=(Button)event.getSource();
		String sourceTitle=button.getText();


		//Widget parent=panel.getParent(); // there is a popup

		//if (parent!=null) { //if popped out, hide it
		//if (parent.getClass().equals(PopupPanel.class)) {
		if (sourceTitle.compareTo(constants.confirm())==0) {
			panel.update();
			settingsSvc.update(panel.getSettings(), new UpdateSettingsCallback(panel));
		}
		//		PopupPanel popup=(PopupPanel)parent;
		//	popup.hide();
		MenuPopup.getSettingsPopup().hide();
	}
	//} else {
	//panel.setVisible(false);

	@Override
	public void onMouseOut(MouseOutEvent event) {
		// TODO Auto-generated method stub
	}
}
