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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import dilmaj.client.MyPanel;
import dilmaj.client.SettingsListener;
import dilmaj.client.TermSummaryPanel;
import dilmaj.client.insert_suggestion.InsertSuggestionPanel;
import dilmaj.client.settings.SettingsPanel;
import dilmaj.client.sidePanel.TableRow;
import dilmaj.shared.*;

public class AllTermsPanel extends VerticalPanel implements SettingsListener, MyPanel {
	private FlexTable termsTable=new FlexTable();
	private List<TableRow> rows=new ArrayList<TableRow>();

	private int termsPerPage=SettingsPanel.getInstance().getTermsPerPage();
	private int currentIndex=0;
	 
	private Label nextButton=new Label(">>");
	private Label prevButton=new Label("<<");
	private Label nextButtonTop=new Label(">>");
	private Label prevButtonTop=new Label("<<");
	
	private static AllTermsPanel theInstance=null;
	
	public static AllTermsPanel getInstance() {
		if (theInstance==null)
			theInstance=new AllTermsPanel();
		
		return theInstance;
	}
	
	private AllTermsPanel() {
		termsTable.setWidth(""+GlobalSettings.getBrowserWidth()*GlobalSettings.getTermsPanelRatio()+"px");
		termsTable.setHeight(""+GlobalSettings.getBrowserHeight()*GlobalSettings.getTermsPanelHeightRatio()+"px");
		
		HorizontalPanel navigationPanel=new HorizontalPanel();
		navigationPanel.setStyleName("navigationTable");
		FlexTable navigationTable=new FlexTable();
		navigationTable.setStyleName("navigationTable");
		navigationPanel.add(navigationTable);
		navigationTable.setWidget(0, 0, prevButton);
		navigationTable.setWidget(0, 1, nextButton);
		navigationTable.getColumnFormatter().setStyleName(0, "prevButton");
		navigationTable.getColumnFormatter().setStyleName(1, "nextButton");
		prevButton.setStyleName("termButton");
		nextButton.setStyleName("termButton");
		
		HorizontalPanel navigationPanelTop=new HorizontalPanel();
		navigationPanelTop.setStyleName("navigationTable");
		FlexTable navigationTableTop=new FlexTable();
		navigationTableTop.setStyleName("navigationTable");
		navigationPanelTop.add(navigationTableTop);
		navigationTableTop.setWidget(0, 0, prevButtonTop);
		navigationTableTop.setWidget(0, 1, nextButtonTop);
		navigationTableTop.getColumnFormatter().setStyleName(0, "prevButton");
		navigationTableTop.getColumnFormatter().setStyleName(1, "nextButton");
		prevButtonTop.setStyleName("termButton");
		nextButtonTop.setStyleName("termButton");
		
		add(navigationPanelTop);
		add(termsTable);
		add(navigationPanel);
		
		AllTermsController controller=new AllTermsController(this);
		nextButton.addClickHandler(controller);
		prevButton.addClickHandler(controller);
		nextButtonTop.addClickHandler(controller);
		prevButtonTop.addClickHandler(controller);
		AllTerms.TheInstance.setAllTermsPanel(this);
		
		SettingsPanel.getInstance().addChangeListener(this);
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
			termsTable.getRowFormatter().setStyleName(row, "termTable");
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
				termsTable.getRowFormatter().setStyleName(row, "termTable");
				row++;
			}
			
			currentIndex=i;
			if (currentIndex>=rows.size())
				currentIndex=0;
		}
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

	@Override
	public void onChange() {
		// TODO Auto-generated method stub
		currentIndex=0;
		browseNext();
		TermSuggestionsPanel.getInstance().browseFirst();
	}

	@Override
	public void setMessage(MessageComposite messageVO) {
		// TODO Auto-generated method stub
		
	}
}
