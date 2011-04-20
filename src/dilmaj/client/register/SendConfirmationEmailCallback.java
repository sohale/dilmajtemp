package dilmaj.client.register;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.shared.EmailComposite;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.MessageComposite;

public class SendConfirmationEmailCallback implements AsyncCallback<MessageComposite> {
	RegisterPanel panel;
	
	public SendConfirmationEmailCallback(RegisterPanel panel) {
		this.panel=panel;
	}
	
	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		panel.setMessage(new MessageComposite("Email Error!"));
	}

	@Override
	public void onSuccess(MessageComposite result) {
		// TODO Auto-generated method stub
		panel.setMessage(new MessageComposite("Email Sent!"));
	}
}
