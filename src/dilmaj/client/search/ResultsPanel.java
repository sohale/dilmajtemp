package dilmaj.client.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dilmaj.client.MyPanel;
import dilmaj.client.insert_term.InsertTermPanel;
import dilmaj.client.sidePanel.AllTermsPanel;
import dilmaj.client.termPanel.TermButton;
import dilmaj.client.termPanel.TermSummaryController;
import dilmaj.client.termPanel.TermSummaryPanel;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.SearchResult;
import dilmaj.shared.TermComposite;

public class ResultsPanel extends PopupPanel implements MyPanel {
	List<SearchResult> foundTerms=new ArrayList<SearchResult>();
	private AllTermsPanel allPanel;
	private MessageComposite messageVO;
	private Label closeButton=new Label("x");
	
	private static ResultsPanel resultsPanel=null;
	
	public static ResultsPanel getInstance(List<SearchResult> foundTerms, String newTerm) {
		resultsPanel=new ResultsPanel(foundTerms, newTerm);
		return resultsPanel;
	}
	
	/*public static ResultsPanel getInstance(String newTerm) {
		resultsPanel=new ResultsPanel(newTerm);
		return resultsPanel;
	}*/
	
	private ResultsPanel(List<SearchResult> foundTerms, String newTerm) {
		setStyleName("transparentPopup");
		
		TextBox inputBox=SearchPanel.getInstance().getSearchBox();
		int left=inputBox.getAbsoluteLeft();
		int top=inputBox.getAbsoluteTop();
		int width=inputBox.getOffsetWidth();
		int height=inputBox.getOffsetHeight();
		
		this.setPopupPosition(left, top+height);
		this.setWidth(width+"px");
		
		this.foundTerms=foundTerms;

		Iterator<SearchResult> iterator=foundTerms.iterator();
		VerticalPanel mainPanel=new VerticalPanel();
		mainPanel.setStyleName("transparentPopup");
		mainPanel.setWidth(width+"px");
		FlexTable termsTable=new FlexTable();
		termsTable.setWidth(width+"px");
		closeButton.setStyleName("termSplitButton");
		mainPanel.add(closeButton);
		mainPanel.add(termsTable);
		
		if (!newTerm.equals("")) {
			FlexTable insertTermTable=new FlexTable();
			Label messageLabel=new Label(newTerm+" "+GlobalSettings.constants.addNewMessage()+":");
			insertTermTable.setWidget(0, 0, messageLabel);
			insertTermTable.setWidget(1, 0, InsertTermPanel.getInstance());
			mainPanel.add(insertTermTable);
		}
		
		int row=0;
		while (iterator.hasNext()) {
			SearchResult result=iterator.next();
			TermComposite aTerm=result.getTermComposite();
			TermButton termButton=new TermButton(aTerm);
			termButton.addClickHandler(TermSummaryController.getController(aTerm));
			termButton.setStyleName("termSplitButton");
			termsTable.setWidget(row++, 0, termButton);
		}
		
		ResultsController controller=new ResultsController(this);
		closeButton.addClickHandler(controller);
		add(mainPanel);
	}
	
	/*private ResultsPanel(String newTerm) {
		TermButton termButton=new TermButton(newTerm);
		ResultsController controller=new ResultsController(allPanel, this);
		termButton.addClickHandler(controller);
		
		VerticalPanel mainPanel=new VerticalPanel();
		Label label=new Label("add it as new");
		setStyleName("transparentPopup");
		mainPanel.setStyleName("transparentPopup");
		mainPanel.add(label);
		mainPanel.add(termButton);
		add(mainPanel);
	}*/

	@Override
	public void setMessage(MessageComposite messageVO) {
		// TODO Auto-generated method stub
		this.messageVO=messageVO;
		Window.alert(messageVO.toString());
	}
}
