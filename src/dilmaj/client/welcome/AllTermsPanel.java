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
import com.google.gwt.user.client.ui.Widget;

import dilmaj.client.TableRow;
import dilmaj.client.TermSummaryPanel;
import dilmaj.client.insert_suggestion.InsertSuggestionPanel;
import dilmaj.client.settings.SettingsPanel;
import dilmaj.shared.*;

public class AllTermsPanel extends HorizontalPanel {
	private FlexTable termsTable=new FlexTable();
	private List<TableRow> rows=new ArrayList<TableRow>();

	private int termsPerPage=SettingsPanel.getInstance().getTermsPerPage();
	private int currentIndex=0;
	private int prevIndex;
	
	private Button nextButton=new Button("next");
	private Button prevButton=new Button("prev");
	
	private static AllTermsPanel theInstance=null;
	
	public static AllTermsPanel getInstance() {
		if (theInstance==null)
			theInstance=new AllTermsPanel();
		
		return theInstance;
	}
	
	private AllTermsPanel() {
		add(termsTable);
		add(nextButton);
		add(prevButton);
		AllTermsController controller=new AllTermsController(this);
		nextButton.addClickHandler(controller);
		prevButton.addClickHandler(controller);
		AllTerms.TheInstance.setAllTermsPanel(this);
	}
	
	public void browseFirst() {
		currentIndex=0;
		prevIndex=rows.size()-1;
		browseNext();
		TermSuggestionsPanel.getInstance().browseFirst();
	}
	
	public void browseNext() {
		termsPerPage=SettingsPanel.getInstance().getTermsPerPage();
		
		termsTable.clear();
		
		int i;
		int row=0;
		for (i=currentIndex;i<currentIndex+termsPerPage && i<rows.size();i++) {
			Iterator<Widget> widgetIterator=rows.get(i).getWidgets().iterator();
			
			int col=0;
			while (widgetIterator.hasNext()) {
				Widget widget=widgetIterator.next();
				
				termsTable.setWidget(row, col, widget);
				col++;
			}
			
			row++;
		}
		
		currentIndex=i;
		if (currentIndex>=rows.size())
			currentIndex=0;
	}
	
	public void browsePrev() {
		termsPerPage=SettingsPanel.getInstance().getTermsPerPage();
		
		termsTable.clear();
		
		int i;
		int row=0;
		
		if (currentIndex==0)
			currentIndex=(rows.size()/termsPerPage)*termsPerPage-termsPerPage;
		else if (currentIndex==termsPerPage)
			currentIndex=(rows.size()/termsPerPage)*termsPerPage;
		else
			currentIndex=currentIndex-2*termsPerPage;
		
		for (i=currentIndex;i<currentIndex+termsPerPage && i<rows.size();i++) {
			Iterator<Widget> widgetIterator=rows.get(i).getWidgets().iterator();
			
			int col=0;
			while (widgetIterator.hasNext()) {
				Widget widget=widgetIterator.next();
				
				termsTable.setWidget(row, col, widget);
				col++;
			}
			
			row++;
		}
		
		currentIndex=i;
		if (currentIndex>=rows.size())
			currentIndex=0;
	}
	
	public void updateTermsTable(TermComposite newTerm) {
		TermSummaryPanel termPanel=TermSummaryPanel.getSummaryPanel(newTerm);
		//termsTable.setWidget(AllTerms.TheInstance.getTerms().size(), 0, termPanel);
		TableRow tableRow=new TableRow();
		tableRow.addWidget(termPanel);
		rows.add(tableRow);
	}

	public void populateTable() {
		// TODO Auto-generated method stub
		Iterator<Long> iterator=AllTerms.TheInstance.getTerms().keySet().iterator();
		while (iterator.hasNext()) {
			Long key=iterator.next();
			TermComposite termComposite=AllTerms.TheInstance.getTerms().get(key);
			if (termComposite.getSuggestions().size()==0) {
				TermSummaryPanel termPanel=TermSummaryPanel.getSummaryPanel(termComposite);
				TableRow tableRow=new TableRow();
				tableRow.addWidget(termPanel);
				rows.add(tableRow);
			}
		}
		
		TermSuggestionsPanel.getInstance().populateTable();
		//tsPanel.browseNext();
		//browseFirst();
	}
}
