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

public class OpenSessionCallback implements AsyncCallback<Integer> {
	private SessionServiceAsync sessionSvc = GWT.create(SessionService.class);
	
	public OpenSessionCallback() {
	}

	@Override
	public void onFailure(Throwable caught) {
	}

	@Override
	public void onSuccess(Integer result) {
		//panel.setSessionID(result);
		sessionSvc.getLog(result, new GetLogCallback());
		LiveLogPanel.getInstance().setMaxMessageId(result);
	}
}
