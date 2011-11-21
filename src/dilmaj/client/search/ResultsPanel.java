package dilmaj.client.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import dilmaj.client.MyPanel;
import dilmaj.client.sidePanel.AllTermsPanel;
import dilmaj.client.termPanel.TermButton;
import dilmaj.client.termPanel.TermSummaryController;
import dilmaj.client.termPanel.TermSummaryPanel;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.SearchResult;
import dilmaj.shared.TermComposite;

public class ResultsPanel extends PopupPanel implements MyPanel {
	List<SearchResult> foundTerms=new ArrayList<SearchResult>();
	private AllTermsPanel allPanel;
	private MessageComposite messageVO;
	private Button closeButton=new Button("x");
	
	private static ResultsPanel resultsPanel=null;
	
	public static ResultsPanel getInstance(List<SearchResult> foundTerms) {
		resultsPanel=new ResultsPanel(foundTerms);
		return resultsPanel;
	}
	
	public static ResultsPanel getInstance(String newTerm) {
		resultsPanel=new ResultsPanel(newTerm);
		return resultsPanel;
	}
	
	private ResultsPanel(List<SearchResult> foundTerms) {
		setStyleName("transparentPopup");
		
		this.foundTerms=foundTerms;

		Iterator<SearchResult> iterator=foundTerms.iterator();
		VerticalPanel mainPanel=new VerticalPanel();
		mainPanel.setStyleName("transparentPopup");
		while (iterator.hasNext()) {
			SearchResult result=iterator.next();
			TermComposite aTerm=result.getTermComposite();
			TermSummaryPanel panel=TermSummaryPanel.getSummaryPanel(aTerm);
			mainPanel.add(panel);
		}
		
		mainPanel.add(closeButton);
		ResultsController controller=new ResultsController(this);
		closeButton.addClickHandler(controller);
		add(mainPanel);
	}
	
	private ResultsPanel(String newTerm) {
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
	}

	@Override
	public void setMessage(MessageComposite messageVO) {
		// TODO Auto-generated method stub
		this.messageVO=messageVO;
		Window.alert(messageVO.toString());
	}
}
