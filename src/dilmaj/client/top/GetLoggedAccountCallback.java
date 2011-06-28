package dilmaj.client.top;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.client.MyPanel;
import dilmaj.client.SettingsService;
import dilmaj.client.SettingsServiceAsync;
import dilmaj.client.insert_term.InsertTermController;
import dilmaj.client.insert_term.InsertTermPanel;
import dilmaj.client.login.FindSettingsCallback;
import dilmaj.shared.EmailComposite;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.MessageComposite;
	
public class GetLoggedAccountCallback implements AsyncCallback<MemberComposite> {
	private SettingsServiceAsync settingsSvc = GWT.create(SettingsService.class);
	InsertTermPanel termPanel=null;
	InsertTermController termController;
	String termCaption;
	
	public GetLoggedAccountCallback() {
	}
	
	public GetLoggedAccountCallback(InsertTermPanel termPanel, InsertTermController termController, String termCaption) {
		this.termPanel=termPanel;
		this.termController=termController;
		this.termCaption=termCaption;
	}
	
	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		if (termPanel!=null)
			termPanel.setMessage(new MessageComposite("Check Login Error!"));
		else
			TopMenu.getInstance().setMessage(new MessageComposite("Check Login Error!"));
	}

	@Override
	public void onSuccess(MemberComposite result) {
		// TODO Auto-generated method stub
		if (result!=null) {
			TopMenu.getInstance().login(result.getUsername());
			settingsSvc.find(result.getUsername(), new FindSettingsCallback(result));
		}
		
		/*if (termPanel!=null)
			termController.insertTerm(termCaption, result);*/

	}
}
