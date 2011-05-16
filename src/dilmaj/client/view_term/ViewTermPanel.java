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

import dilmaj.client.TermButton;
import dilmaj.client.welcome.AllTermsPanel;
import dilmaj.client.welcome.WelcomeController;
import dilmaj.shared.*;

public class ViewTermPanel extends HorizontalPanel {
	private MessageComposite message;
	private AllTermsPanel allPanel;
	private ViewTermController controller;
	private TermComposite theTerm;
	private Button closeButton=new Button(GlobalSettings.constants.close());
	private Button addSuggestion=new Button("add new suggestion");
	private PopupPanel popup=null;

	private HashMap<String, TermSuggestionComposite> suggestions=new HashMap<String, TermSuggestionComposite>();
	
	private VerticalPanel termPanel=new VerticalPanel();
	private Label termLabel;
	private FlexTable suggestionsTable=new FlexTable();
	
	public ViewTermPanel(TermComposite theTerm, PopupPanel popup) {
		controller=new ViewTermController(this, allPanel, popup);
		this.theTerm=theTerm;

		termLabel=new Label(GlobalSettings.constants.term()+" "+theTerm.getCaption());
		termPanel.add(termLabel);
		
		suggestionsTable.setText(0, 0, GlobalSettings.constants.rank());
		suggestionsTable.setText(0, 1, GlobalSettings.constants.suggestion());
		int row=1;
		Iterator<TermSuggestionComposite> tsIterator=theTerm.getSuggestions().iterator();
		while (tsIterator.hasNext()) {
			TermSuggestionComposite tsVO=tsIterator.next();
			Label suggestionLabel=new Label(tsVO.getSuggestion().getCaption());
			suggestions.put(tsVO.getSuggestion().getCaption(), tsVO);
			
			suggestionLabel.addMouseOverHandler(controller);
			
			suggestionsTable.setWidget(row, 1, suggestionLabel);
			suggestionsTable.setText(row, 0, tsVO.getRank()+"");
			row++;
		}
		termPanel.add(suggestionsTable);
		/*add(termBox);
		add(insertButton);
		add(languageBox);*/
		add(termPanel);
		add(addSuggestion);
		add(closeButton);
		
		/*languageBox.addItem(GlobalSettings.constants.english());
		languageBox.addItem(GlobalSettings.constants.french());
		languageBox.addItem(GlobalSettings.constants.arabic());
		languageBox.setVisibleItemCount(1);*/
		
		addSuggestion.addClickHandler(controller);
		closeButton.addClickHandler(controller);
		
		//termBox.setText(theTerm.getCaption());
		
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
}
