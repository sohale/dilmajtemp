package dilmaj.client.view_term;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.client.sidePanel.AllTermsPanel;
import dilmaj.shared.Controller;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.Repository;
import dilmaj.shared.TermComposite;

public class ViewTermCallback implements AsyncCallback<TermComposite> {
	ViewTermPanel panel;
	AllTermsPanel allPanel;
	
	public ViewTermCallback(ViewTermPanel panel, AllTermsPanel allPanel) {
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
		panel.setMessage(new MessageComposite(result.getId()+""));
		panel.setTerm(result);
	}
}
