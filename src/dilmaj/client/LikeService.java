package dilmaj.client;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dilmaj.shared.LikeComposite;
import dilmaj.shared.TermComposite;

@RemoteServiceRelativePath("likeservice")
public interface LikeService extends RemoteService {
	public LikeComposite create(LikeComposite likeVO);
}
