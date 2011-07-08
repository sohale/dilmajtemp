package dilmaj.client.welcome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import dilmaj.client.TableRow;
import dilmaj.client.TermButton;
import dilmaj.client.TermSummaryPanel;
import dilmaj.client.insert_suggestion.InsertSuggestionPanel;
import dilmaj.client.settings.SettingsPanel;
import dilmaj.shared.*;

public class TermSuggestionsPanel extends VerticalPanel {
	private FlexTable tsTable=new FlexTable();
	private Set<TermComposite> termSet=new HashSet<TermComposite>();
	private AllTermsPanel allTermsPanel;
	private List<TableRow> rows=new ArrayList<TableRow>();
	
	private int termsPerPage=SettingsPanel.getInstance().getTermsPerPage();
	private int currentIndex=0;
	private int prevIndex;
	
	private Button nextButton=new Button("next");
	private Button prevButton=new Button("prev");
	
	private static TermSuggestionsPanel theInstance=null;
	
	private int maxLength;
	
	public static TermSuggestionsPanel getInstance() {
		if (theInstance==null)
			theInstance=new TermSuggestionsPanel();
		return theInstance;
	}
	
	private TermSuggestionsPanel() {
		SettingsComposite settingsVO=SettingsPanel.getInstance().getSettings();
		if (settingsVO!=null)
			termsPerPage=settingsVO.getTermsPerPage();
		add(tsTable);
		HorizontalPanel navigationPanel=new HorizontalPanel();
		navigationPanel.add(prevButton);
		navigationPanel.add(nextButton);
		add(navigationPanel);
		TermSuggestionController controller=new TermSuggestionController(this);
		nextButton.addClickHandler(controller);
		prevButton.addClickHandler(controller);
	}
	
	public void browseFirst() {
		currentIndex=0;
		prevIndex=rows.size()-1;
		browseNext();
	}
	
	public void browseNext() {
		termsPerPage=SettingsPanel.getInstance().getTermsPerPage();
		
		tsTable.clear();
		
		int i;
		int row=0;
		for (i=currentIndex;i<currentIndex+termsPerPage && i<rows.size();i++) {
			Iterator<Widget> widgetIterator=rows.get(i).getWidgets().iterator();
			
			int col=0;
			while (widgetIterator.hasNext()) {
				Widget widget=widgetIterator.next();
				
				tsTable.setWidget(row, col, widget);
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
		
		if (rows.size()>termsPerPage){
			tsTable.clear();
			
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
					
					tsTable.setWidget(row, col, widget);
					col++;
				}
				
				row++;
			}
			
			currentIndex=i;
			if (currentIndex>=rows.size())
				currentIndex=0;
		}
	}
	
	public void populateTable() {
		Iterator<Long> iterator=AllTerms.TheInstance.getTerms().keySet().iterator();
		while (iterator.hasNext()) {
			Long key=iterator.next();
			TermComposite termVO=AllTerms.TheInstance.getTerms().get(key);
			
			Iterator<TermSuggestionComposite> tsIterator=termVO.getSuggestions().iterator();
			if (tsIterator.hasNext()) {
				//TermButton termButton=new TermButton(termVO);
				TermSummaryPanel termPanel=TermSummaryPanel.getSummaryPanel(termVO);
				//tsTable.setWidget(row, 0, termPanel);
				
				TableRow tableRow=new TableRow();
				tableRow.addWidget(termPanel);
				
				//int col=1;
				/*while (tsIterator.hasNext()) {
					TermComposite suggestionVO=tsIterator.next().getSuggestion();
					TermSummaryPanel suggestionPanel=TermSummaryPanel.getSummaryPanel(suggestionVO);
					//tsTable.setWidget(row, col, suggestionPanel);
					
					tableRow.addWidget(suggestionPanel);
					
					//col++;
				}*/
				rows.add(tableRow);
				//row++;
			}
		}
		
		AllTermsPanel.getInstance().browseFirst();
	}
}
