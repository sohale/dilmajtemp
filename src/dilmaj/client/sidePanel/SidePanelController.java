package dilmaj.client.sidePanel;

import com.google.gwt.core.client.GWT;

import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;

public class SidePanelController {
	private TermServiceAsync termSvc = GWT.create(TermService.class);

	public void populateMe(TermsTable source, int from, int to) {
		if (source.name().compareTo("TermSuggestionTable")==0) {
			termSvc.getTermsWithSuggestion(from, to, GetTermSuggestionsCallback.getInstance());
		}
	}
}
