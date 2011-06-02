package dilmaj.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dilmaj.shared.LikeComposite;

@RemoteServiceRelativePath("likeservice")
public interface LikeService extends RemoteService {
	public LikeComposite create(LikeComposite likeVO);
}
