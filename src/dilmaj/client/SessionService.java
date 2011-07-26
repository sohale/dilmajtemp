package dilmaj.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dilmaj.shared.LikeComposite;
import dilmaj.shared.MessageComposite;

@RemoteServiceRelativePath("sessionservice")
public interface SessionService extends RemoteService {
	public List<MessageComposite> getLog();
	public String openSession();
	public void closeSession(String myID);
}
