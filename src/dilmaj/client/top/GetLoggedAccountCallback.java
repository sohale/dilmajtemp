package dilmaj.client.top;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.client.MyPanel;
import dilmaj.client.insert_term.InsertTermController;
import dilmaj.client.insert_term.InsertTermPanel;
import dilmaj.shared.EmailComposite;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.MessageComposite;
	
public class GetLoggedAccountCallback implements AsyncCallback<String> {
	TopMenu topMenu=null;
	
	InsertTermPanel termPanel=null;
	InsertTermController termController;
	String termCaption;
	
	public GetLoggedAccountCallback(TopMenu topMenu) {
		this.topMenu=topMenu;
	}
	
	public GetLoggedAccountCallback(InsertTermPanel termPanel, InsertTermController termController, String termCaption) {
		this.termPanel=termPanel;
		this.termController=termController;
		this.termCaption=termCaption;
	}
	
	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		if (topMenu!=null)
			topMenu.setMessage(new MessageComposite("Check Login Error!"));
		if (termPanel!=null)
			termPanel.setMessage(new MessageComposite("Check Login Error!"));
	}

	@Override
	public void onSuccess(String result) {
		// TODO Auto-generated method stub
		if (result!=null) {
			if (topMenu!=null)
				topMenu.login(result);
		}
		
		if (termPanel!=null)
			termController.insertTerm(termCaption, result);

	}
}
