package dilmaj.client.view_term;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;

import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;
import dilmaj.client.insert_suggestion.InsertSuggestionPanel;
import dilmaj.client.sidePanel.AllTermsPanel;
import dilmaj.client.termPanel.TermButton;
import dilmaj.client.view_suggestion.ViewSuggestionPanel;
import dilmaj.shared.Controller;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.Repository;
import dilmaj.shared.TermComposite;
import dilmaj.shared.TermSuggestionComposite;

public class ViewTermController extends Controller {
	private TermServiceAsync termSvc = GWT.create(TermService.class);
	private ViewTermPanel viewTermPanel;
	private AllTermsPanel allPanel;
	private PopupPanel popup;
	private HashMap<ViewSuggestionPanel, PopupPanel> sPopups=new HashMap<ViewSuggestionPanel, PopupPanel>();
	
	public ViewTermController(ViewTermPanel termPanel, AllTermsPanel allPanel) {
		viewTermPanel=termPanel;
		this.allPanel=allPanel;
	}
	
	public ViewTermController(ViewTermPanel termPanel, AllTermsPanel allPanel, PopupPanel popup) {
		viewTermPanel=termPanel;
		this.allPanel=allPanel;
		this.popup=popup;
	}
	
	public void getTerm(Long id) {
		TermComposite termVO=new TermComposite();
		termVO.setId(id);
		
		termSvc.get(termVO, new ViewTermCallback(viewTermPanel, allPanel));
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		String caption=((Button)event.getSource()).getText();
		if (caption.compareTo(GlobalSettings.constants.close())==0)
			popup.hide();
		if (caption.compareTo("add new suggestion")==0) {
			InsertSuggestionPanel.getInstance().setTerm(viewTermPanel.getTerm());
			InsertSuggestionPanel.getInstance().setVisible(true);
		}
	}

	@Override
	public void onMouseOver(MouseOverEvent event) {
		// TODO Auto-generated method stub
		/*Label sourceLabel=(Label)event.getSource();
		String label=sourceLabel.getText();
		
		TermSuggestionComposite tsVO=viewTermPanel.getSuggestion(label);
		ViewSuggestionPanel sPanel=ViewSuggestionPanel.getInstance(tsVO);
		
		RootPanel.get("termSuggestionDetails").clear();
		RootPanel.get("termSuggestionDetails").add(sPanel);*/

		/*PopupPanel sPopup=sPopups.get(sPanel);
		if (sPopup==null) {
			sPopup=new PopupPanel();
			int left=sourceLabel.getAbsoluteLeft();
			int bottom=sourceLabel.getAbsoluteTop()+sourceLabel.getOffsetHeight();
			sPopup.setPopupPosition(left, bottom);
			sPopup.add(sPanel);
			sPopups.put(sPanel, sPopup);
		}
		sPopup.show();*/
	}
	
	public void setPopup(PopupPanel popup) {
		this.popup=popup;
	}
}
