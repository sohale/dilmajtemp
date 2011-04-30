package dilmaj.client;

import java.util.HashMap;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import dilmaj.client.insert_suggestion.InsertSuggestionPanel;
import dilmaj.client.insert_term.InsertTermPanel;
import dilmaj.shared.TermComposite;

public class TermSummaryController implements ClickHandler {
	private static HashMap<TermComposite, TermSummaryController> controllers=new HashMap<TermComposite, TermSummaryController>();
	
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
		InsertSuggestionPanel.getInstance().setTerm(((TermButton)event.getSource()).getTermComposite());
	}

}
