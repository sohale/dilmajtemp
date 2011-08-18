package dilmaj.client.livelog;

import dilmaj.client.SessionService;
import dilmaj.client.SessionServiceAsync;
import dilmaj.client.SettingsService;
import dilmaj.client.SettingsServiceAsync;
import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;
import dilmaj.client.settings.SettingsPanel;
import dilmaj.client.top.TopMenu;
import dilmaj.client.view_my_terms.LoadMyTermsCallback;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.SettingsComposite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class OpenSessionCallback implements AsyncCallback<Void> {
	private SessionServiceAsync sessionSvc = GWT.create(SessionService.class);
	LiveLogPanel panel;
	
	public OpenSessionCallback(LiveLogPanel panel) {
		this.panel=panel;
	}

	@Override
	public void onFailure(Throwable caught) {
	}

	@Override
	public void onSuccess(Void result) {
		//panel.setSessionID(result);
		//sessionSvc.getLog(new GetLogCallback(panel));
		//panel.setMaxMessageId(result);
	}
}
