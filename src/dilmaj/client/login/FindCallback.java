package dilmaj.client.login;

import dilmaj.client.SettingsService;
import dilmaj.client.SettingsServiceAsync;
import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;
import dilmaj.client.view_my_terms.LoadMyTermsCallback;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.SettingsComposite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class FindCallback implements AsyncCallback<MemberComposite> {
	private TermServiceAsync termSvc = GWT.create(TermService.class);
	private SettingsServiceAsync settingsSvc = GWT.create(SettingsService.class);
	LoginPanel panel;
	
	public FindCallback(LoginPanel panel) {
		this.panel=panel;
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		panel.setMessage(new MessageComposite("Error!"));
		
	}

	@Override
	public void onSuccess(MemberComposite result) {
		// TODO Auto-generated method stub
		if (result==null)
			panel.setMessage(new MessageComposite("User not found!"));
		else {
			//panel.setMessage(new MessageComposite(result.getId()+""));
			panel.login(result.getUsername()); //May call TopMenu automatically.
			termSvc.getMyTerms(new LoadMyTermsCallback());
			SettingsComposite settingsVO=result.getSettings();
			if (settingsVO==null) {
				settingsSvc.create(result.getUsername(), new CreateSettingsCallback(result));
			}
		}
	}
}
