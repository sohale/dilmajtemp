package dilmaj.client.register;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.shared.EmailComposite;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.MessageComposite;

public class CreateUserCallback implements AsyncCallback<MemberComposite> {
	RegisterPanel panel;
	
	public CreateUserCallback(RegisterPanel panel) {
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
		panel.setMessage(new MessageComposite(result.getId()+""));
		panel.getController().sendConfirmationEmail(result);
	}
}
