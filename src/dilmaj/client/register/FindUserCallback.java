package dilmaj.client.register;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.client.DilmajUserService;
import dilmaj.client.DilmajUserServiceAsync;
import dilmaj.client.TestGWT;
import dilmaj.shared.EmailComposite;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.MessageComposite;

public class FindUserCallback implements AsyncCallback<MemberComposite> {
	private DilmajUserServiceAsync userSvc = GWT.create(DilmajUserService.class);
	TestGWT panel;
	
	public FindUserCallback(TestGWT panel) {
		this.panel=panel;
	}
	
	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		panel.setMessage(new MessageComposite("An error happenned, you're either already confirmed your registeration or have never logged in"));
	}

	@Override
	public void onSuccess(MemberComposite result) {
		// TODO Auto-generated method stub
		/*result.setActivator("permanent");
		userSvc.update(result, new UpdateUserCallback(panel));*/
		panel.setMessage(new MessageComposite("you may now login"));
	}
}
