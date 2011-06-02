package dilmaj.client.login;

import dilmaj.client.view_my_terms.MyTerms;
import dilmaj.shared.MessageComposite;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class LogoutCallback implements AsyncCallback<Void> {
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
	public void onSuccess(Void result) {
		// TODO Auto-generated method stub
		panel.logout();
		MyTerms.TheInstance.clear();
	}
}
