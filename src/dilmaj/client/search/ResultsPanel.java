package dilmaj.client.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import dilmaj.client.MyPanel;
import dilmaj.client.TermButton;
import dilmaj.client.TermSummaryController;
import dilmaj.client.TermSummaryPanel;
import dilmaj.client.welcome.AllTermsPanel;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.TermComposite;

public class ResultsPanel extends PopupPanel implements MyPanel {
	List<TermComposite> foundTerms=new ArrayList<TermComposite>();
	private AllTermsPanel allPanel;
	private MessageComposite messageVO;
	private Button closeButton=new Button("x");
	
	private static ResultsPanel resultsPanel=null;
	
	public static ResultsPanel getInstance(List<TermComposite> foundTerms) {
		resultsPanel=new ResultsPanel(foundTerms);
		return resultsPanel;
	}
	
	public static ResultsPanel getInstance(String newTerm) {
		resultsPanel=new ResultsPanel(newTerm);
		return resultsPanel;
	}
	
	private ResultsPanel(List<TermComposite> foundTerms) {
		this.foundTerms=foundTerms;

		Iterator<TermComposite> iterator=foundTerms.iterator();
		VerticalPanel mainPanel=new VerticalPanel();
		while (iterator.hasNext()) {
			TermComposite aTerm=iterator.next();
			//ResultsController controller=new ResultsController(this);
			TermSummaryPanel panel=TermSummaryPanel.getSummaryPanel(aTerm);
			mainPanel.add(panel);
		}
		
		mainPanel.add(closeButton);
		ResultsController controller=new ResultsController(this);
		closeButton.addClickHandler(controller);
		add(mainPanel);
	}
	
	private ResultsPanel(String newTerm) {
		this.allPanel=allPanel;
		TermButton termButton=new TermButton(newTerm);
		ResultsController controller=new ResultsController(allPanel, this);
		termButton.addClickHandler(controller);
		
		VerticalPanel mainPanel=new VerticalPanel();
		Label label=new Label("add it as new");
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
