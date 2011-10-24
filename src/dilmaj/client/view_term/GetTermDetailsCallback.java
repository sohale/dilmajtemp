package dilmaj.client.view_term;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

import dilmaj.client.DilmajConstants;
import dilmaj.client.WaitPanel;
import dilmaj.client.sidePanel.AllTermsPanel;
import dilmaj.shared.Controller;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.Repository;
import dilmaj.shared.TermComposite;
import dilmaj.shared.TermSuggestionComposite;

public class GetTermDetailsCallback implements AsyncCallback<TermComposite> {
	private DilmajConstants constants = GWT.create(DilmajConstants.class);
	
	private static GetTermDetailsCallback theInstance=null;
	
	public static GetTermDetailsCallback getInstance() {
		if (theInstance==null)
			theInstance=new GetTermDetailsCallback();
		
		return theInstance;
	}
	
	private GetTermDetailsCallback() {
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSuccess(TermComposite result) {
		// TODO Auto-generated method stub
		if (RootPanel.get("termDetails").getWidgetCount()==0)
			RootPanel.get("termDetails").add(ViewTermPanel.getInstance().populateWith(result));
		else
			ViewTermPanel.getInstance().populateWith(result);
		WaitPanel.getInstance().removeMessage(constants.termDetailsBeingLoaded());

		//ViewTermPanel.getInstance().populateWith(result);
	}
}
