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
	private TermComposite theTerm;
	private Button closeButton=new Button(GlobalSettings.constants.close());
	private Button addSuggestion=new Button("add new suggestion");
	private PopupPanel popup=null;

	private HashMap<String, TermSuggestionComposite> suggestions=new HashMap<String, TermSuggestionComposite>();
	
	private HorizontalPanel termPanel=new HorizontalPanel();
	private FlexTable termSummaryTable=new FlexTable();
	private Label termLabel;
	private FlexTable suggestionsTable=new FlexTable();
	
	private static ViewTermPanel viewTermPanel=null;
		
	private Label languageLabel;
	private Label userLabel;
	
	private ViewTermPanel() {
		setStyleName("termDetailsPanel");
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
		ViewTermController.getInstance().setPopup(popup);
	}

	// to be called by callback only!
	public ViewTermPanel populateWith(TermComposite theTerm) {
		setWidth(""+GlobalSettings.getBrowserWidth()*GlobalSettings.getTermDetailsPanelRatio()+"px");
		setHeight(""+GlobalSettings.getBrowserHeight()*GlobalSettings.getTermDetailsPanelHeightRatio()*theTerm.getSuggestions().size()+"px");
		suggestionsTable.setWidth(""+GlobalSettings.getBrowserWidth()*GlobalSettings.getTermDetailsPanelRatio()+"px");
		suggestionsTable.setHeight(""+GlobalSettings.getBrowserHeight()*GlobalSettings.getTermDetailsPanelHeightRatio()*theTerm.getSuggestions().size()+"px");
		
		termPanel.clear();
		suggestionsTable.clear();
		clear();
		
		this.theTerm=theTerm;
		termPanel.add(termSummaryTable);

		termLabel=new Label(GlobalSettings.constants.term()+":"+theTerm.getCaption());
		termSummaryTable.setWidget(0, 0, termLabel);
		
		languageLabel=new Label(GlobalSettings.constants.language()+":"+Language.getLanguage(theTerm.getLanguage()).toString());
		termSummaryTable.setWidget(0, 1, languageLabel);
		
		userLabel=new Label(GlobalSettings.constants.creator()+":"+theTerm.getUser());
		termSummaryTable.setWidget(0, 2, userLabel);
		
		termSummaryTable.getRowFormatter().setStyleName(0, "termTableHeader");
		
		int row=0;
		int nComments=0, nSamples=0;
		Iterator<TermSuggestionComposite> tsIterator=theTerm.getSuggestions().iterator();
		while (tsIterator.hasNext()) {
			TermSuggestionComposite tsVO=tsIterator.next();
			//Label suggestionLabel=new Label(tsVO.getSuggestion().getCaption());
			suggestions.put(tsVO.getSuggestion().getCaption(), tsVO);
			
			//suggestionLabel.addMouseOverHandler(controller);
			
			ViewSuggestionPanel suggestionPanel=ViewSuggestionPanel.getInstance(tsVO);
			nComments+=suggestionPanel.getCommentsCount();
			nSamples+=suggestionPanel.getSamplesCount();
			
			suggestionsTable.setWidget(row, 0, suggestionPanel);
			if (row%2==0)
				suggestionsTable.getRowFormatter().setStyleName(row, "evenTableRow");
			else
				suggestionsTable.getRowFormatter().setStyleName(row, "oddTableRow");
			//suggestionsTable.setText(row, 0, tsVO.getLikes()+"");
			row++;
		}
		StringBuilder sb=new StringBuilder(theTerm.getSuggestions().size()+" "+GlobalSettings.constants.suggestion()+" ");
		sb.append(nComments+" "+GlobalSettings.constants.comment()+" ");
		sb.append(nSamples+" "+GlobalSettings.constants.usecase());
				
		Label suggestionsLabel=new Label(sb.toString());
		termSummaryTable.setWidget(0, 3, suggestionsLabel);
		
		add(termPanel);
		add(suggestionsTable);
		add(addSuggestion);
		add(closeButton);
		
		addSuggestion.addClickHandler(ViewTermController.getInstance());
		closeButton.addClickHandler(ViewTermController.getInstance());
		
		//this.popup=popup;
		
		return viewTermPanel;
	}
	
	// to be called by term button and summary controller 
	public static void initializeWith(TermComposite theTerm) {
		// TODO Auto-generated method stub
		//ViewTermPanel viewTermPanel=viewTermPanels.get(theTerm);
		if (viewTermPanel==null) {
			viewTermPanel=new ViewTermPanel();
			//viewTermPanels.put(theTerm, viewTermPanel);
		}
		
		ViewTermController.getInstance().getTermDetails(theTerm);
	}
	
	public static ViewTermPanel getInstance() {
		if (viewTermPanel==null) {
			viewTermPanel=new ViewTermPanel();
		}
		
		return viewTermPanel;
	}
}
