package dilmaj.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.shared.LikeComposite;

public interface InteractionServiceAsync {

	void create(LikeComposite likeVO, AsyncCallback<LikeComposite> callback);

}
