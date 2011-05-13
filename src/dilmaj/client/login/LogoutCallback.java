package dilmaj.client.login;

import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;
import dilmaj.client.view_my_terms.LoadMyTermsCallback;
import dilmaj.client.view_my_terms.MyTerms;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.UserComposite;
import dilmaj.shared.MessageComposite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class LogoutCallback implements AsyncCallback {
	LoginPanel panel;
	
	public LogoutCallback(LoginPanel panel) {
		this.panel=panel;
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		panel.setMessage(new MessageComposite("Error!"));
		
	}

	@Override
	public void onSuccess(Object result) {
		// TODO Auto-generated method stub
		panel.logout();
		MyTerms.TheInstance.clear();
	}
}
