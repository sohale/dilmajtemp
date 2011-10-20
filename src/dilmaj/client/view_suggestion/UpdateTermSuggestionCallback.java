package dilmaj.client.view_suggestion;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.client.sidePanel.AllTermsPanel;
import dilmaj.shared.Controller;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.Repository;
import dilmaj.shared.TermComposite;
import dilmaj.shared.TermSuggestionComposite;

public class UpdateTermSuggestionCallback implements AsyncCallback<TermSuggestionComposite> {
	ViewSuggestionPanel panel;
	
	public UpdateTermSuggestionCallback(ViewSuggestionPanel panel) {
		this.panel=panel;
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		panel.setMessage(new MessageComposite("Error Increasing Rank!"));
	}

	@Override
	public void onSuccess(TermSuggestionComposite result) {
		// TODO Auto-generated method stub
		//panel.setMessage(new MessageComposite(result.getId()+""));
		panel.updateTermSuggestionRank(result);
	}
}
