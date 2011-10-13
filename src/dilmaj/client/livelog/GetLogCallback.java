package dilmaj.client.livelog;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.client.SessionService;
import dilmaj.client.SessionServiceAsync;
import dilmaj.shared.MessageComposite;

public class GetLogCallback implements AsyncCallback<List<MessageComposite>> {
	private SessionServiceAsync sessionSvc = GWT.create(SessionService.class);
	//LiveLogPanel panel;
	
	public GetLogCallback() {
	}
	
	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSuccess(List<MessageComposite> result) {
		// TODO Auto-generated method stub
		//replace these with a timer based mechanism
		
		//LiveLogPanel.getInstance().addMessages(result);
		
		//sessionSvc.getLog(LiveLogPanel.getInstance().getLastMessageId(), this/*new GetLogCallback()*/);
	}
}
