package dilmaj.client.login;

import dilmaj.client.settings.SettingsPanel;
import dilmaj.client.top.TopMenu;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.SettingsComposite;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class CreateSettingsCallback implements AsyncCallback<SettingsComposite> {
	MemberComposite theMember;
	
	public CreateSettingsCallback(MemberComposite member) {
		theMember=member;
	}

	@Override
	public void onFailure(Throwable caught) {
	}

	@Override
	public void onSuccess(SettingsComposite result) {
		theMember.setSettings(result);
		SettingsPanel.getInstance().setSettings(result);
	}
}
