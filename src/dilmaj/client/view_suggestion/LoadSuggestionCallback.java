package dilmaj.client.view_suggestion;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

import dilmaj.client.welcome.AllTermsPanel;
import dilmaj.shared.Controller;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.Repository;
import dilmaj.shared.TermComposite;
import dilmaj.shared.TermSuggestionComposite;

public class LoadSuggestionCallback implements AsyncCallback<TermSuggestionComposite> {
	
	public LoadSuggestionCallback() {
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSuccess(TermSuggestionComposite result) {
		// TODO Auto-generated method stub
		RootPanel.get("termDetails").clear();
		RootPanel.get("termDetails").add(ViewSuggestionPanel.getInstance(result));
	}
}
