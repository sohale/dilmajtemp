package dilmaj.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dilmaj.shared.CommentComposite;
import dilmaj.shared.LikeComposite;
import dilmaj.shared.UseCaseComposite;

@RemoteServiceRelativePath("interactionservice")
public interface InteractionService extends RemoteService {
	public LikeComposite create(LikeComposite likeVO);
	public CommentComposite create(CommentComposite likeVO);
	public UseCaseComposite create(UseCaseComposite likeVO);
}
