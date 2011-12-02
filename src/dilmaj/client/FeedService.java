package dilmaj.client;

import java.util.Date;

import javax.xml.transform.stream.StreamResult;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.xml.client.Document;

@RemoteServiceRelativePath("feedservice")
public interface FeedService extends RemoteService {
	String getFeed(Date beginDate);
}
