package dilmaj.client.livelog;

import com.google.gwt.core.client.GWT;

import dilmaj.client.SessionService;
import dilmaj.client.SessionServiceAsync;
import dilmaj.shared.Controller;

public class LiveLogController extends Controller {
	private SessionServiceAsync sessionSvc = GWT.create(SessionService.class);
	private LiveLogPanel panel;
	
	public LiveLogController(LiveLogPanel panel) {
		this.panel=panel;
	}
	
	public void openSession() {
		sessionSvc.openSession(new OpenSessionCallback(panel));
	}

	public void closeSession(String sessionID) {
		sessionSvc.closeSession(sessionID, new CloseSessionCallback());
	}
}