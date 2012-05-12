package dilmaj.client.sidePanel;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.client.WaitPanel;
import dilmaj.shared.Controller;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.TermComposite;

public class GetTermsOnlyCallback implements AsyncCallback<List<TermComposite>> {
	//AllTermsPanel panel;
	private static GetTermsOnlyCallback theInstance=null;
	
	public static GetTermsOnlyCallback getInstance() {
		if (theInstance==null)
			theInstance=new GetTermsOnlyCallback();
		
		return theInstance;
	}
	
	private GetTermsOnlyCallback() {
		//this.panel=panel;
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		//AllTermsPanel.getInstance().setMessage(new MessageComposite("Error Loading All Terms!"));
		Window.alert("Load all terms failed!");
	}

	@Override
	public void onSuccess(List<TermComposite> result) {
		//Window.alert(result.size()+"");
		TermsTable.TermOnlyTable.populate(result);
	}

}
