package dilmaj.client.view_my_terms;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;

import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;
import dilmaj.shared.Controller;
import dilmaj.shared.TermComposite;

public enum MyTermsController {
	myTermsController;
	
	private static TermServiceAsync termSvc = GWT.create(TermService.class);
		
	public static void loadMyTerms() {
		termSvc.getMyTerms(new LoadMyTermsCallback());
	}
}
