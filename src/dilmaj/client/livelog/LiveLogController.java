package dilmaj.client.livelog;

import java.io.Serializable;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;

import dilmaj.client.SessionService;
import dilmaj.client.SessionServiceAsync;
import dilmaj.shared.Controller;

public class LiveLogController extends Controller {
	private SessionServiceAsync sessionSvc = GWT.create(SessionService.class);
	
	public LiveLogController() {
	}
	
	public void openSession() {
		sessionSvc.openSession(new OpenSessionCallback());
	}

	public void closeSession(String sessionID) {
		sessionSvc.closeSession(sessionID, new CloseSessionCallback());
	}

	@Override
	public void onClick(ClickEvent event) {
		sessionSvc.getLog(LiveLogPanel.getInstance().getLastMessageId(), new GetLogCallback());
	}
}
