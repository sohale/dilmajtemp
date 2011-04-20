package dilmaj.client.search;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;

import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;
import dilmaj.client.welcome.AllTermsPanel;
import dilmaj.shared.Controller;
import dilmaj.shared.TermComposite;

public class SearchController extends Controller implements KeyUpHandler, ClickHandler {
	private SearchPanel panel;
	private boolean atEnd=true;
	private AllTermsPanel allPanel;
	private TermServiceAsync termSvc = GWT.create(TermService.class);
	
	public SearchController(SearchPanel searchPanel, AllTermsPanel allPanel) {
		panel=searchPanel;
		this.allPanel=allPanel;
	}
	
	@Override
	public void onKeyUp(KeyUpEvent event) {
		// TODO Auto-generated method stub
		String input=panel.getInputText();
		input=input.trim();
		String[] subInputs=input.split(" ");
		panel.setButtons(subInputs);
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		Button button=(Button)event.getSource();
		String captionFilter=button.getText();
		termSvc.getSome(captionFilter, new GetSomeTermsCallback(captionFilter, allPanel, button.getAbsoluteLeft(), button.getAbsoluteTop()));
	}
}
