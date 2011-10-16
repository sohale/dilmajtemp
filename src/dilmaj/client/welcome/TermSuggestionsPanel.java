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
	private List<TableRow> rows=new ArrayList<TableRow>();
	
	private int termsPerPage=SettingsPanel.getInstance().getTermsPerPage();
	private int currentIndex=0;
	private int prevIndex;
	
	private Label nextButton=new Label(">>");
	private Label prevButton=new Label("<<");
	private Label nextButtonTop=new Label(">>");
	private Label prevButtonTop=new Label("<<");
	
	private static TermSuggestionsPanel theInstance=null;
	
	public static TermSuggestionsPanel getInstance() {
		if (theInstance==null)
			theInstance=new TermSuggestionsPanel();
		return theInstance;
	}
	
	private TermSuggestionsPanel() {
		SettingsComposite settingsVO=SettingsPanel.getInstance().getSettings();
		if (settingsVO!=null)
			termsPerPage=settingsVO.getTermsPerPage();
		
		tsTable.setWidth(""+GlobalSettings.getBrowserWidth()*GlobalSettings.getTermsPanelRatio()+"px");
		tsTable.setHeight(""+GlobalSettings.getBrowserHeight()*GlobalSettings.getTermsPanelHeightRatio()+"px");
		
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
		add(tsTable);
		add(navigationPanel);
		
		TermSuggestionController controller=new TermSuggestionController(this);
		nextButton.addClickHandler(controller);
		prevButton.addClickHandler(controller);
		nextButtonTop.addClickHandler(controller);
		prevButtonTop.addClickHandler(controller);
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
			tsTable.getRowFormatter().setStyleName(row, "termTable");
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
				tsTable.getRowFormatter().setStyleName(row, "termTable");
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
