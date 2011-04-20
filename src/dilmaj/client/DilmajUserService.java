package dilmaj.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dilmaj.shared.EmailComposite;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.UserComposite;

@RemoteServiceRelativePath("dilmajuserservice")
public interface DilmajUserService extends RemoteService {
	MemberComposite create(MemberComposite userVO);
	MessageComposite sendMail(EmailComposite emailVO);
	MemberComposite find(MemberComposite userVO);
	MemberComposite update(MemberComposite userVO);
	String getLoggedUser();
}
