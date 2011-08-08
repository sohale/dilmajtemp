package dilmaj.client.livelog;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.client.SessionService;
import dilmaj.client.SessionServiceAsync;
import dilmaj.shared.MessageComposite;

public class GetLogCallback implements AsyncCallback<List<MessageComposite>> {
	private SessionServiceAsync sessionSvc = GWT.create(SessionService.class);
	LiveLogPanel panel;
	
	public GetLogCallback(LiveLogPanel panel) {
		this.panel=panel;
	}
	
	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSuccess(List<MessageComposite> result) {
		// TODO Auto-generated method stub
		panel.addMessages(result);
		//sessionSvc.getLog(new GetLogCallback(panel));
	}
}
