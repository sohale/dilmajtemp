package dilmaj.client.login;

import dilmaj.client.DilmajUserService;
import dilmaj.client.DilmajUserServiceAsync;
import dilmaj.client.SettingsService;
import dilmaj.client.SettingsServiceAsync;
import dilmaj.client.register.UpdateUserCallback;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.SettingsComposite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class UpdateSettingsCallback implements AsyncCallback<SettingsComposite> {
	public UpdateSettingsCallback() {
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSuccess(SettingsComposite result) {
	}
}
