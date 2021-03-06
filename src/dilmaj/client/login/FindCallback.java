package dilmaj.client.login;

import java.util.HashSet;
import java.util.Set;

import dilmaj.client.DilmajUserService;
import dilmaj.client.DilmajUserServiceAsync;
import dilmaj.client.SettingsService;
import dilmaj.client.SettingsServiceAsync;
import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;
import dilmaj.client.sidePanel.TermTabsPanel;
import dilmaj.client.top.TopMenuTabs;
import dilmaj.client.users_panel.GetOnlineUsersCallback;
import dilmaj.client.users_panel.UsersPanel;
import dilmaj.client.view_my_terms.LoadMyTermsCallback;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.MessageComposite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class FindCallback implements AsyncCallback<MemberComposite> {
	private TermServiceAsync termSvc = GWT.create(TermService.class);
	private SettingsServiceAsync settingsSvc = GWT.create(SettingsService.class);
	private DilmajUserServiceAsync accountSvc = GWT.create(DilmajUserService.class);
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
			TopMenuTabs.getInstance().login(result.getUsername());
			termSvc.getMyTerms(new LoadMyTermsCallback());
			settingsSvc.find(result.getUsername(), new FindSettingsCallback(result));
			
			LoginController.getInstance().login();
		}
	}
}
