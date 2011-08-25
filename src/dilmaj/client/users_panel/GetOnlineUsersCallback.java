package dilmaj.client.users_panel;

import java.util.Set;

import dilmaj.client.DilmajUserService;
import dilmaj.client.DilmajUserServiceAsync;
import dilmaj.client.SettingsService;
import dilmaj.client.SettingsServiceAsync;
import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;
import dilmaj.client.settings.SettingsPanel;
import dilmaj.client.top.TopMenu;
import dilmaj.client.view_my_terms.LoadMyTermsCallback;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.SettingsComposite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class GetOnlineUsersCallback implements AsyncCallback<Set<String>> {
	private DilmajUserServiceAsync accountSvc = GWT.create(DilmajUserService.class);
	
	public GetOnlineUsersCallback() {
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		//panel.setMessage(new MessageComposite("Error!"));
	}

	@Override
	public void onSuccess(Set<String> result) {
		// TODO Auto-generated method stub
		UsersPanel.getInstance().updateUsersList(result);
		
		if (UsersPanel.getInstance().isLoggedIn())
			accountSvc.getOnlineUsers(new GetOnlineUsersCallback());
	}
}
