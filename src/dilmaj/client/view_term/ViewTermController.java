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

import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;
import dilmaj.client.view_suggestion.ViewSuggestionPanel;
import dilmaj.client.welcome.AllTermsPanel;
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
	}

	@Override
	public void onMouseOver(MouseOverEvent event) {
		// TODO Auto-generated method stub
		String label=((Label)event.getSource()).getText();
		TermSuggestionComposite tsVO=viewTermPanel.getSuggestion(label);
		ViewSuggestionPanel sPanel=ViewSuggestionPanel.getInstance(tsVO);
		PopupPanel sPopup=sPopups.get(sPanel);
		if (sPopup==null) {
			sPopup=new PopupPanel();
			sPopup.add(sPanel);
			sPopups.put(sPanel, sPopup);
		}
		sPopup.show();
	}
}
