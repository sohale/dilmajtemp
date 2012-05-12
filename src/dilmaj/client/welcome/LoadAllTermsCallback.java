package dilmaj.client.welcome;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.shared.Controller;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.TermComposite;

public class LoadAllTermsCallback implements AsyncCallback<HashMap<Long, TermComposite>> {
	//AllTermsPanel panel;
	
	public LoadAllTermsCallback() {
		//this.panel=panel;
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		//AllTermsPanel.getInstance().setMessage(new MessageComposite("Error Loading All Terms!"));
		Window.alert("Load all terms failed!");
	}

	@Override
	public void onSuccess(HashMap<Long, TermComposite> result) {
		// TODO Auto-generated method stub
		AllTerms.TheInstance.setTerms(result);
		
		//panel.populateTable();
	}

}
