package dilmaj.client.welcome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

import dilmaj.client.TermSummaryPanel;
import dilmaj.client.insert_suggestion.InsertSuggestionPanel;
import dilmaj.shared.*;

public class AllTermsPanel extends HorizontalPanel {
	private FlexTable termsTable=new FlexTable();
	//private HashMap<Long, TermComposite> allTerms;
	//private WelcomeController controller;
	//private InsertSuggestionPanel insertPanel;
	private TermSuggestionsPanel tsPanel;
	
	public AllTermsPanel(TermSuggestionsPanel tsPanel) {
		add(termsTable);
		//this.insertPanel=insertPanel;
		this.tsPanel=tsPanel;
		//controller=new WelcomeController(this, insertPanel);
		AllTerms.TheInstance.setAllTermsPanel(this);
		//while (!AllTerms.TheInstance.isLoaded()) {}
		//populateTable();
	}
	
	public void updateTermsTable(TermComposite newTerm) {
		TermSummaryPanel termPanel=TermSummaryPanel.getSummaryPanel(newTerm);
		termsTable.setWidget(AllTerms.TheInstance.getTerms().size(), 0, termPanel);
	}
	
	/*public List<TermComposite> getSome(String captionFilter) {
		List<TermComposite> someTerms=new ArrayList<TermComposite>();
		
		Iterator<Long> iterator=allTerms.keySet().iterator();
		while (iterator.hasNext()) {
			Long key=iterator.next();
			TermComposite term=allTerms.get(key);
			String caption=term.getCaption();
			
			if (caption!=null)
				if (caption.startsWith(captionFilter))
					someTerms.add(term);
		}
		
		return someTerms;
	}*/

	public void populateTable() {
		// TODO Auto-generated method stub
		//allTerms=AllTerms.TheInstance.getTerms();
		
		Iterator<Long> iterator=AllTerms.TheInstance.getTerms().keySet().iterator();
		int row=0;
		while (iterator.hasNext()) {
			Long key=iterator.next();
			TermSummaryPanel termPanel=TermSummaryPanel.getSummaryPanel(AllTerms.TheInstance.getTerms().get(key));
			/*TermButton termButton=new TermButton(allTerms.get(key));
			termButton.addClickHandler(controller);*/
			termsTable.setWidget(row++, 0, termPanel);
		}
		
		tsPanel.populateTable();
	}
}
