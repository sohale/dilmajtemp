package dilmaj.client.register;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.client.TestGWT;
import dilmaj.shared.EmailComposite;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.MessageComposite;

public class UpdateUserCallback implements AsyncCallback<MemberComposite> {
	TestGWT panel;
	
	public UpdateUserCallback(TestGWT panel) {
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
		panel.setMessage(new MessageComposite("you may now login"));
	}
}
