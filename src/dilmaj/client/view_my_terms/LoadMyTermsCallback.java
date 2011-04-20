package dilmaj.client.view_my_terms;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.shared.Controller;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.TermComposite;

public class LoadMyTermsCallback implements AsyncCallback<HashMap<Long, TermComposite>> {
	public LoadMyTermsCallback() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		//panel.setMessage(new MessageComposite("Error!"));
	}

	@Override
	public void onSuccess(HashMap<Long, TermComposite> result) {
		// TODO Auto-generated method stub
		MyTerms.TheInstance.setTerms(result);
	}

}
