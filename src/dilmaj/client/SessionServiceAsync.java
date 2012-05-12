package dilmaj.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.shared.MessageComposite;

public interface SessionServiceAsync {

	void getLog(int lastId, AsyncCallback<List<MessageComposite>> callback);

	void openSession(AsyncCallback<Integer> callback);

	void closeSession(String myID, AsyncCallback<Void> callback);

}
