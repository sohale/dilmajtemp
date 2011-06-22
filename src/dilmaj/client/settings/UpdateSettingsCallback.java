package dilmaj.client.settings;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.client.welcome.AllTermsPanel;
import dilmaj.shared.Controller;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.Repository;
import dilmaj.shared.SettingsComposite;
import dilmaj.shared.TermComposite;
import dilmaj.shared.TermSuggestionComposite;

public class UpdateSettingsCallback implements AsyncCallback<SettingsComposite> {
	SettingsPanel panel;
	
	public UpdateSettingsCallback(SettingsPanel panel) {
		this.panel=panel;
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		panel.setMessage(new MessageComposite("Error Increasing Rank!"));
	}

	@Override
	public void onSuccess(SettingsComposite result) {
		// TODO Auto-generated method stub
		//panel.setMessage(new MessageComposite(result.getId()+""));
		//panel.updateTermSuggestionRank(result);
	}
}
