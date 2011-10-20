package dilmaj.client.view_term;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

import dilmaj.client.sidePanel.AllTermsPanel;
import dilmaj.shared.Controller;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.Repository;
import dilmaj.shared.TermComposite;
import dilmaj.shared.TermSuggestionComposite;

public class LoadTermCallback implements AsyncCallback<TermComposite> {
	
	public LoadTermCallback() {
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSuccess(TermComposite result) {
		// TODO Auto-generated method stub
		RootPanel.get("termDetails").clear();
		RootPanel.get("termDetails").add(ViewTermPanel.getInstance(result));
	}
}
