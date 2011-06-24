package dilmaj.client.welcome;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import dilmaj.client.TermButton;
import dilmaj.shared.Controller;

public class TermSuggestionController extends Controller {
	TermSuggestionsPanel panel;
	
	public TermSuggestionController(TermSuggestionsPanel tsPanel) {
		panel=tsPanel;
	}
	
	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		panel.browseNext();
	}

}
