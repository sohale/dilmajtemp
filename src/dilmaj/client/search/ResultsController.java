package dilmaj.client.search;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;

import dilmaj.client.MyPanel;
import dilmaj.client.TermButton;
import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;
import dilmaj.client.insert_term.InsertTermCallback;
import dilmaj.client.view_term.ViewTermPanel;
import dilmaj.client.welcome.AllTermsPanel;
import dilmaj.shared.Controller;
import dilmaj.shared.TermComposite;

public class ResultsController extends Controller implements ClickHandler {
	private TermServiceAsync termSvc = GWT.create(TermService.class);
	private AllTermsPanel allPanel;
	private ResultsPanel aPanel;

	public ResultsController(AllTermsPanel allPanel, ResultsPanel aPanel) {
		this.allPanel=allPanel;
		this.aPanel=aPanel;
	}
	
	public ResultsController(ResultsPanel aPanel) {
		this.aPanel=aPanel;
	}
	
	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		try {
			TermButton termButton=(TermButton)event.getSource();
			String title=termButton.getText();
			TermComposite termVO=termButton.getTermComposite();
			if (termVO!=null) {
				PopupPanel popup=new PopupPanel();
				ViewTermPanel viewTerm=ViewTermPanel.getInstance(termButton.getTermComposite(), popup);
				popup.add(viewTerm);
				int left=termButton.getAbsoluteLeft();
				int top=termButton.getAbsoluteTop();
				popup.setPopupPosition(left, top);
				popup.show();
			} else {
				termVO=new TermComposite(termButton.getText());
				termSvc.addEntry(termVO, new InsertTermCallback(aPanel));
			}
		} catch (ClassCastException cce) {
			Button button=(Button)event.getSource();
			aPanel.hide();
		}
	}
}
