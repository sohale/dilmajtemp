package dilmaj.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dilmaj.shared.SettingsComposite;

@RemoteServiceRelativePath("settingsservice")
public interface SettingsService extends RemoteService {
	SettingsComposite update(SettingsComposite settingsVO);
}
