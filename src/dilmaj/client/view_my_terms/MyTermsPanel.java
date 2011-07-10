package dilmaj.client.view_my_terms;

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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import dilmaj.client.TableRow;
import dilmaj.client.TermSummaryController;
import dilmaj.client.TermSummaryPanel;
import dilmaj.client.insert_suggestion.InsertSuggestionPanel;
import dilmaj.client.settings.SettingsPanel;
import dilmaj.client.welcome.TermSuggestionsPanel;
import dilmaj.shared.*;

public class MyTermsPanel extends VerticalPanel {
	private FlexTable termsTable=new FlexTable();
	private List<TableRow> rows=new ArrayList<TableRow>();

	private int termsPerPage=SettingsPanel.getInstance().getTermsPerPage();
	private int currentIndex=0;
	
	private Button nextButton=new Button("next");
	private Button prevButton=new Button("prev");
	
	private MyTermsController controller=new MyTermsController(this);
	
	private static MyTermsPanel theInstance=null;
	
	private boolean isLoggedIn=false;
	
	public static MyTermsPanel getInstance() {
		if (theInstance==null)
			theInstance=new MyTermsPanel();
		
		return theInstance;
	}
	
	private MyTermsPanel() {
		add(termsTable);
		
		MyTerms.TheInstance.setMyPanel(this);
		
		HorizontalPanel navigationPanel=new HorizontalPanel();
		navigationPanel.add(prevButton);
		navigationPanel.add(nextButton);
		add(navigationPanel);
	}
	
	public void populateTable() {
		if (MyTerms.TheInstance.getTerms()!=null) {
			//Window.alert("My Terms Loaded : "+MyTerms.TheInstance.getTerms().size());
			Iterator<Long> iterator=MyTerms.TheInstance.getTerms().keySet().iterator();
			int row=0;
			while (iterator.hasNext()) {
				Long key=iterator.next();
				TermComposite termVO=MyTerms.TheInstance.getTerms().get(key);
				TermSummaryPanel termPanel=TermSummaryPanel.getSummaryPanel(termVO);
				//termsTable.setWidget(row++, 0, termPanel);
				TableRow tableRow=new TableRow();
				tableRow.addWidget(termPanel);
				rows.add(tableRow);
			}
		} else {
			rows.clear();
			termsTable.removeAllRows();
		}
	}
	
	public void updateTermsTable(TermComposite newTerm) {
		TermSummaryPanel termPanel=TermSummaryPanel.getSummaryPanel(newTerm);
		termsTable.setWidget(MyTerms.TheInstance.getTerms().size(), 0, termPanel);
	}
	public void browseFirst() {
		currentIndex=0;
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
		
		if (rows.size()>termsPerPage){
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
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
}
