package dilmaj.client.login;

import dilmaj.shared.MemberComposite;
import dilmaj.shared.UserComposite;
import dilmaj.shared.MessageComposite;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class FindCallback implements AsyncCallback<MemberComposite> {
	LoginPanel panel;
	
	public FindCallback(LoginPanel panel) {
		this.panel=panel;
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		panel.setMessage(new MessageComposite("Error!"));
		
	}

	@Override
	public void onSuccess(MemberComposite result) {
		// TODO Auto-generated method stub
		if (result==null)
			panel.setMessage(new MessageComposite("User not found!"));
		else {
			//panel.setMessage(new MessageComposite(result.getId()+""));
			panel.login(result.getUsername());
		}
	}
}
