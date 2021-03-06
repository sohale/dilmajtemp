package dilmaj.client.termPanel;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import dilmaj.client.DilmajConstants;
import dilmaj.client.WaitPanel;
import dilmaj.client.view_term.ViewTermPanel;
import dilmaj.shared.TermComposite;

public class TermSummaryController implements ClickHandler {
	private static HashMap<TermComposite, TermSummaryController> controllers=new HashMap<TermComposite, TermSummaryController>();
	private static TermButton selectedTermButton=null;
	private DilmajConstants constants = GWT.create(DilmajConstants.class);
	
	private TermSummaryController() {
		
	}
	
	public static TermSummaryController getController(TermComposite termVO) {
		TermSummaryController controller=controllers.get(termVO);
		if (controller==null) {
				controller=new TermSummaryController();
				controllers.put(termVO, controller);
		}
		return controller;
	}
	
	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
		TermButton termButton=(TermButton)event.getSource();
		
		Widget parent=termButton.getParent().getParent();
		
		if (!parent.getClass().equals(TermSummaryPanel.class)) {
			Widget popupPanel=parent.getParent();
			((PopupPanel)popupPanel).hide();
		}
		
		if (selectedTermButton!=null) {
			selectedTermButton.setStyleName("termButton");
		}
		selectedTermButton=termButton;
		termButton.setStyleName("termButtonClicked");
		
		TermComposite termComposite=termButton.getTermComposite();
		
		WaitPanel.getInstance().addMessage(constants.termDetailsBeingLoaded());
		ViewTermPanel.initializeWith(termComposite/*, popup*/);
	}

}
