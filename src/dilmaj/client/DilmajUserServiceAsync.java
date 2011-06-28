package dilmaj.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.shared.EmailComposite;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.MessageComposite;

public interface DilmajUserServiceAsync {

	void create(MemberComposite userVO, AsyncCallback<MemberComposite> callback);

	void sendMail(EmailComposite emailVO,
			AsyncCallback<MessageComposite> callback);

	void find(MemberComposite userVO, AsyncCallback<MemberComposite> callback);

	void update(MemberComposite userVO, AsyncCallback<MemberComposite> callback);

	void getLoggedUser(AsyncCallback<MemberComposite> callback);

	void logout(AsyncCallback<Void> callback);
}
