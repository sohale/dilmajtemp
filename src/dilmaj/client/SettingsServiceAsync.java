package dilmaj.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.shared.MemberComposite;
import dilmaj.shared.SettingsComposite;

public interface SettingsServiceAsync {

	void update(SettingsComposite settingsVO,
			AsyncCallback<SettingsComposite> callback);

	void create(MemberComposite userVO,
			AsyncCallback<SettingsComposite> callback);

	void find(String username, AsyncCallback<SettingsComposite> callback);

}
