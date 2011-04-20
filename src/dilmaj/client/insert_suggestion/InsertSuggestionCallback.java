package dilmaj.client.insert_suggestion;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.client.welcome.AllTermsPanel;
import dilmaj.shared.Controller;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.Repository;
import dilmaj.shared.TermComposite;

public class InsertSuggestionCallback implements AsyncCallback<TermComposite> {
	InsertSuggestionPanel panel;
	
	public InsertSuggestionCallback(InsertSuggestionPanel panel) {
		this.panel=panel;
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		panel.setMessage(new MessageComposite("Error!"));
	}

	@Override
	public void onSuccess(TermComposite result) {
		// TODO Auto-generated method stub
		if (result==null)
			panel.setMessage(new MessageComposite("Did you login?!"));
		else
			panel.getController().createTermSuggestion(panel.getTerm(), result);
			
		//panel.setMessage(new MessageComposite(result.getId()+""));
		//Repository.allTermsPanel.updateTermsTable(result);
	}
}
