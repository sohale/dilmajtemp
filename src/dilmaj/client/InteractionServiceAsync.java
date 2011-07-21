package dilmaj.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.shared.CommentComposite;
import dilmaj.shared.LikeComposite;
import dilmaj.shared.UseCaseComposite;

public interface InteractionServiceAsync {

	void create(LikeComposite likeVO, AsyncCallback<LikeComposite> callback);

	void create(CommentComposite likeVO,
			AsyncCallback<CommentComposite> callback);

	void create(UseCaseComposite likeVO,
			AsyncCallback<UseCaseComposite> callback);

}
