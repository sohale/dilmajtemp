package dilmaj.client;

import java.util.Date;

import javax.xml.transform.stream.StreamResult;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.xml.client.Document;

public interface FeedServiceAsync {

	void getFeed(Date beginDate, AsyncCallback<String> callback);

}
