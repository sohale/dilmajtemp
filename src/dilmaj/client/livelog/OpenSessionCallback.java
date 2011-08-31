package dilmaj.client.livelog;

import dilmaj.client.SessionService;
import dilmaj.client.SessionServiceAsync;

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
