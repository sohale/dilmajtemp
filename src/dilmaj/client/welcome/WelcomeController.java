package dilmaj.client.welcome;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

import dilmaj.client.TermButton;
import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;
import dilmaj.client.TermSuggestionService;
import dilmaj.client.TermSuggestionServiceAsync;
import dilmaj.client.insert_suggestion.InsertSuggestionPanel;
import dilmaj.shared.Controller;
import dilmaj.shared.Repository;
import dilmaj.shared.TermComposite;

public class WelcomeController extends Controller implements ClickHandler {
	private TermServiceAsync termSvc = GWT.create(TermService.class);
	private TermSuggestionServiceAsync tsSvc = GWT.create(TermSuggestionService.class);
	private AllTermsPanel allTermPanel;
	private InsertSuggestionPanel insertPanel;
	private TermSuggestionsPanel tsPanel;
	
	public WelcomeController(AllTermsPanel panel, InsertSuggestionPanel insertPanel) {
		allTermPanel=panel;
		this.insertPanel=insertPanel;
		//termSvc.loadAll(new LoadAllTermsCallback(allTermPanel));
	}
	
	public void updateTermsList(TermComposite newTerm) {
		allTermPanel.updateTermsTable(newTerm);
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		insertPanel.setTerm(((TermButton)event.getSource()).getTermComposite());
	}
}
