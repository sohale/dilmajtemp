package dilmaj.client;

import java.util.HashMap;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;

import dilmaj.client.view_term.ViewTermPanel;
import dilmaj.shared.TermComposite;

public class TermSummaryController implements ClickHandler {
	private static HashMap<TermComposite, TermSummaryController> controllers=new HashMap<TermComposite, TermSummaryController>();
	private static TermButton selectedTermButton=null;
	
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
		
		if (selectedTermButton!=null) {
			selectedTermButton.setStyleName("termButton");
		}
		selectedTermButton=termButton;
		termButton.setStyleName("termButtonClicked");
		
		TermComposite termComposite=termButton.getTermComposite();
		
		//PopupPanel popup=new PopupPanel();
		ViewTermPanel viewTermPanel=ViewTermPanel.getInstance(termComposite/*, popup*/);
		
		RootPanel.get("termDetails").clear();
		RootPanel.get("termDetails").add(viewTermPanel);
		RootPanel.get("termSuggestionDetails").clear();

		/*popup.setSize("100px", "100px");
		
		int left=termButton.getAbsoluteLeft();
		int bottom=termButton.getAbsoluteTop()+termButton.getOffsetHeight();
		popup.setPopupPosition(left, bottom);
		
		popup.add(viewTermPanel);
		
		popup.show();*/
	}

}
