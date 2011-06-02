package dilmaj.client.insert_suggestion;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;
import dilmaj.client.TermSuggestionService;
import dilmaj.client.TermSuggestionServiceAsync;
import dilmaj.shared.Controller;
import dilmaj.shared.TermComposite;
import dilmaj.shared.TermSuggestionComposite;

public class InsertSuggestionController extends Controller implements ClickHandler {
	private TermServiceAsync termSvc = GWT.create(TermService.class);
	private TermSuggestionServiceAsync termsuggestionSvc = GWT.create(TermSuggestionService.class);
	private InsertSuggestionPanel insertPanel;
	
	public InsertSuggestionController() {
	}
	
	public void insertSuggestion(String caption, TermComposite term) {
		TermComposite termVO=new TermComposite(caption);
		
		termSvc.addEntry(termVO, new InsertSuggestionCallback(insertPanel));
	}
	
	public void createTermSuggestion(TermComposite term, TermComposite suggestion) {
		TermSuggestionComposite termSuggestion=new TermSuggestionComposite(term, suggestion);
		
		termsuggestionSvc.create(termSuggestion, new CreateTermSuggestionCallback(insertPanel));
	}
	
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		insertPanel=(InsertSuggestionPanel)((Button)event.getSource()).getParent();
		
		insertSuggestion(insertPanel.getSuggestionCaption(), insertPanel.getTerm());
	}
}
