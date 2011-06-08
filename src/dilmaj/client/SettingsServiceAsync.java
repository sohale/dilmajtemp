package dilmaj.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.shared.SettingsComposite;

public interface SettingsServiceAsync {

	void update(SettingsComposite settingsVO,
			AsyncCallback<SettingsComposite> callback);

}
