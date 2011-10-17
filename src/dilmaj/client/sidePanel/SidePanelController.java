package dilmaj.client.sidePanel;

import com.google.gwt.core.client.GWT;

import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;

public class SidePanelController {
	private TermServiceAsync termSvc = GWT.create(TermService.class);

	public void loadMe(TermsTable source, int from, int to) {
		if (source==TermsTable.TermSuggestionTable) {
			termSvc.getTermsWithSuggestion(from, to, GetTermSuggestionsCallback.getInstance());
		}
	}
}
