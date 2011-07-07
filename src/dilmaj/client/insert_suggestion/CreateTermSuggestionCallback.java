package dilmaj.client.insert_suggestion;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.shared.MessageComposite;
import dilmaj.shared.TermSuggestionComposite;

public class CreateTermSuggestionCallback implements AsyncCallback<TermSuggestionComposite> {
	InsertSuggestionPanel panel;
	
	public CreateTermSuggestionCallback(InsertSuggestionPanel panel) {
		this.panel=panel;
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		panel.setMessage(new MessageComposite("Error!"));
		panel.setVisible(false);
	}

	@Override
	public void onSuccess(TermSuggestionComposite result) {
		// TODO Auto-generated method stub
		panel.setMessage(new MessageComposite(result.getId()+""));
		panel.setVisible(false);
	}
}
