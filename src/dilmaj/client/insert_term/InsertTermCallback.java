package dilmaj.client.insert_term;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.client.MyPanel;
import dilmaj.client.welcome.AllTermsPanel;
import dilmaj.shared.Controller;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.Repository;
import dilmaj.shared.TermComposite;

public class InsertTermCallback implements AsyncCallback<TermComposite> {
	MyPanel panel;
	AllTermsPanel allPanel;
	
	public InsertTermCallback(MyPanel panel, AllTermsPanel allPanel) {
		this.panel=panel;
		this.allPanel=allPanel;
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		panel.setMessage(new MessageComposite("Error!"));
	}

	@Override
	public void onSuccess(TermComposite result) {
		// TODO Auto-generated method stub
		if (result==null)
			panel.setMessage(new MessageComposite("Did you login?!"));
		else {
			panel.setMessage(new MessageComposite(result.getId()+""));
			allPanel.updateTermsTable(result);
		}
	}
}
