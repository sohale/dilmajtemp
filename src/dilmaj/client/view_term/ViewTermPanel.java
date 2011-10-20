package dilmaj.client.view_term;

import java.util.HashMap;
import java.util.Iterator;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dilmaj.client.sidePanel.AllTermsPanel;
import dilmaj.client.termPanel.TermButton;
import dilmaj.client.view_suggestion.ViewSuggestionPanel;
import dilmaj.client.welcome.WelcomeController;
import dilmaj.shared.*;

public class ViewTermPanel extends VerticalPanel {
	private MessageComposite message;
	private AllTermsPanel allPanel;
	private ViewTermController controller;
	private TermComposite theTerm;
	private Button closeButton=new Button(GlobalSettings.constants.close());
	private Button addSuggestion=new Button("add new suggestion");
	private PopupPanel popup=null;

	private HashMap<String, TermSuggestionComposite> suggestions=new HashMap<String, TermSuggestionComposite>();
	
	private HorizontalPanel termPanel=new HorizontalPanel();
	private Label termLabel;
	private FlexTable suggestionsTable=new FlexTable();
	
	private static HashMap<TermComposite,ViewTermPanel> viewTermPanels=new HashMap<TermComposite,ViewTermPanel>();
	
	private Label languageLabel;
	private Label userLabel;
	
	public static ViewTermPanel getInstance(TermComposite theTerm, PopupPanel popup) {
		ViewTermPanel viewTermPanel=viewTermPanels.get(theTerm);
		if (viewTermPanel==null) {
			viewTermPanel=new ViewTermPanel(theTerm, popup);
			viewTermPanels.put(theTerm, viewTermPanel);
		} else {
			if (popup!=viewTermPanel.getPopup())
				viewTermPanel.setPopup(popup);
		}
		
		return viewTermPanel;
	}
	
	private ViewTermPanel(TermComposite theTerm, PopupPanel popup) {
		controller=new ViewTermController(this, allPanel, popup);
		this.theTerm=theTerm;

		termLabel=new Label(GlobalSettings.constants.term()+":"+theTerm.getCaption());
		termPanel.add(termLabel);
		termPanel.add(new Label(" | "));
		
		languageLabel=new Label(GlobalSettings.constants.language()+":"+Language.getLanguage(theTerm.getLanguage()).toString());
		termPanel.add(languageLabel);
		termPanel.add(new Label(" | "));
		
		userLabel=new Label(GlobalSettings.constants.creator()+":"+theTerm.getUser());
		termPanel.add(userLabel);
		termPanel.add(new Label(" | "));
		
		// removed to create Naser's prototype
		suggestionsTable.setText(0, 0, GlobalSettings.constants.rank());
		suggestionsTable.setText(0, 1, GlobalSettings.constants.suggestion());
		int row=1;
		Iterator<TermSuggestionComposite> tsIterator=theTerm.getSuggestions().iterator();
		while (tsIterator.hasNext()) {
			TermSuggestionComposite tsVO=tsIterator.next();
			//Label suggestionLabel=new Label(tsVO.getSuggestion().getCaption());
			suggestions.put(tsVO.getSuggestion().getCaption(), tsVO);
			
			//suggestionLabel.addMouseOverHandler(controller);
			
			ViewSuggestionPanel suggestionPanel=ViewSuggestionPanel.getInstance(tsVO);
			
			suggestionsTable.setWidget(row, 0, suggestionPanel);
			//suggestionsTable.setText(row, 0, tsVO.getLikes()+"");
			row++;
		}
		add(termPanel);
		add(suggestionsTable);
		add(addSuggestion);
		add(closeButton);
		
		addSuggestion.addClickHandler(controller);
		closeButton.addClickHandler(controller);
		
		this.popup=popup;
	}
	
	public ViewTermPanel(String termId) {
		controller=new ViewTermController(this, allPanel);
		controller.getTerm(Long.parseLong(termId));

		add(termLabel);
		/*add(termBox);
		add(insertButton);
		add(languageBox);*/
		add(closeButton);
		
		closeButton.addClickHandler(controller);
		
		/*languageBox.addItem(GlobalSettings.constants.english());
		languageBox.addItem(GlobalSettings.constants.french());
		languageBox.addItem(GlobalSettings.constants.arabic());
		languageBox.setVisibleItemCount(1);*/
	}
	
	public void setMessage(MessageComposite messageVO) {
		message=messageVO;
		Window.alert(message.toString());
	}
	
	public void setTerm(TermComposite term) {
		theTerm=term;
		termLabel.setText(GlobalSettings.constants.term()+" "+theTerm.getCaption());
	}
	
	public TermSuggestionComposite getSuggestion(String caption) {
		return suggestions.get(caption);
	}
	
	public TermComposite getTerm() {
		return theTerm;
	}
	
	public PopupPanel getPopup() {
		return popup;
	}
	
	public void setPopup(PopupPanel popup) {
		this.popup=popup;
		controller.setPopup(popup);
	}

	public static ViewTermPanel getInstance(TermComposite theTerm) {
		// TODO Auto-generated method stub
		ViewTermPanel viewTermPanel=viewTermPanels.get(theTerm);
		if (viewTermPanel==null) {
			viewTermPanel=new ViewTermPanel(theTerm, null);
			viewTermPanels.put(theTerm, viewTermPanel);
		}
		
		return viewTermPanel;
	}
}
