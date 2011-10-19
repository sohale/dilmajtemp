package dilmaj.client.sidePanel;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.shared.Controller;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.TermComposite;

public class GetTermSuggestionsCallback implements AsyncCallback<List<TermComposite>> {
	//AllTermsPanel panel;
	private static GetTermSuggestionsCallback theInstance=null;
	
	public static GetTermSuggestionsCallback getInstance() {
		if (theInstance==null)
			theInstance=new GetTermSuggestionsCallback();
		
		return theInstance;
	}
	
	private GetTermSuggestionsCallback() {
		//this.panel=panel;
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		//AllTermsPanel.getInstance().setMessage(new MessageComposite("Error Loading All Terms!"));
		Window.alert("Load term suggestions failed!");
	}

	@Override
	public void onSuccess(List<TermComposite> result) {
		Window.alert(result.size()+"");
		TermsTable.TermSuggestionTable.populate(result);
	}

}
