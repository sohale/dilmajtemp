package dilmaj.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.shared.LikeComposite;

public interface LikeServiceAsync {

	void create(LikeComposite likeVO, AsyncCallback<LikeComposite> callback);

}
