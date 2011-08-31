package dilmaj.client.login;

import dilmaj.client.SettingsService;
import dilmaj.client.SettingsServiceAsync;
import dilmaj.client.settings.SettingsPanel;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.SettingsComposite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class FindSettingsCallback implements AsyncCallback<SettingsComposite> {
	MemberComposite theMember;
	private SettingsServiceAsync settingsSvc = GWT.create(SettingsService.class);

	public FindSettingsCallback(MemberComposite member) {
		theMember=member;
	}

	@Override
	public void onFailure(Throwable caught) {
	}

	@Override
	public void onSuccess(SettingsComposite result) {
		if (result!=null) {
			theMember.setSettings(result);
			SettingsPanel.getInstance().setSettings(result);
		} else {
			settingsSvc.create(theMember, new CreateSettingsCallback(theMember));
		}
	}
}
