package dilmaj.client.sidePanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;

public class SidePanelController implements ClickHandler {
	private TermServiceAsync termSvc = GWT.create(TermService.class);

	public void populateMe(TermsTable source, int from, int to) {
		if (source.name().compareTo("TermSuggestionTable")==0) {
			termSvc.getTermsWithSuggestion(from, to, GetTermSuggestionsCallback.getInstance());
		}
	}

	@Override
	public void onClick(ClickEvent event) {
		try {
			Label label=(Label)event.getSource();
			FlexTable flexTable=(FlexTable)label.getParent();
			HorizontalPanel horizontalPanel=(HorizontalPanel)flexTable.getParent();
			
			if (horizontalPanel.getParent().getClass().getName().compareToIgnoreCase("dilmaj.client.sidePanel.TermSuggestionsPanel")==0) {
				//Window.alert("TermSuggestionPanel event caught!");
				if (TermsTable.TermSuggestionTable.endOfRows())
					// Window.alert(+"");
					;
				else
					TermsTable.TermSuggestionTable.browse();
			}

		} catch(Exception e) {
			
		}
	}
}
