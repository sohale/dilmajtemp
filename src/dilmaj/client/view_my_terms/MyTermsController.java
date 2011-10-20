package dilmaj.client.view_my_terms;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;
import dilmaj.client.sidePanel.MyTermsPanel;
import dilmaj.shared.Controller;
import dilmaj.shared.TermComposite;

public class MyTermsController extends Controller {
	private static TermServiceAsync termSvc = GWT.create(TermService.class);
		
	public MyTermsController() {
		// TODO Auto-generated constructor stub
	}

	public static void loadMyTerms() {
		termSvc.getMyTerms(new LoadMyTermsCallback());
	}
	
	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		Label button=(Label)event.getSource();
		String text=button.getText();
		
		/*if (text.compareTo("next")==0)
			MyTermsPanel.getInstance().browseNext();
		else
			MyTermsPanel.getInstance().browsePrev();*/
	}
}
