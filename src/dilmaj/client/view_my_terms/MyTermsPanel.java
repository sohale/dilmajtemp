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

import dilmaj.client.SettingsListener;
import dilmaj.client.TableRow;
import dilmaj.client.TermSummaryController;
import dilmaj.client.TermSummaryPanel;
import dilmaj.client.insert_suggestion.InsertSuggestionPanel;
import dilmaj.client.settings.SettingsPanel;
import dilmaj.client.welcome.TermSuggestionsPanel;
import dilmaj.shared.*;

public class MyTermsPanel extends VerticalPanel implements SettingsListener {
	private FlexTable termsTable=new FlexTable();
	private List<TableRow> rows=new ArrayList<TableRow>();

	private int termsPerPage=SettingsPanel.getInstance().getTermsPerPage();
	private int currentIndex=0;
	
	private Label nextButton=new Label(">>");
	private Label prevButton=new Label("<<");
	private Label nextButtonTop=new Label(">>");
	private Label prevButtonTop=new Label("<<");
	private MyTermsController controller=new MyTermsController();
	private static MyTermsPanel theInstance=null;
	
	private boolean isLoggedIn=false;
	
	public static MyTermsPanel getInstance() {
		if (theInstance==null)
			theInstance=new MyTermsPanel();
		
		return theInstance;
	}
	
	private MyTermsPanel() {
		termsTable.setWidth(""+GlobalSettings.getBrowserWidth()*GlobalSettings.getTermsPanelRatio()+"px");
		termsTable.setHeight(""+GlobalSettings.getBrowserHeight()*GlobalSettings.getTermsPanelHeightRatio()+"px");
		
		MyTerms.TheInstance.setMyPanel(this);
		
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

		nextButton.addClickHandler(controller);
		prevButton.addClickHandler(controller);
		nextButtonTop.addClickHandler(controller);
		prevButtonTop.addClickHandler(controller);
		
		SettingsPanel.getInstance().addChangeListener(this);
	}
	
	public void populateTable() {
		if (MyTerms.TheInstance.getTerms()!=null) {
			//Window.alert("My Terms Loaded : "+MyTerms.TheInstance.getTerms().size());
			Iterator<Long> iterator=MyTerms.TheInstance.getTerms().keySet().iterator();
			int row=0;
			while (iterator.hasNext()) {
				Long key=iterator.next();
				TermComposite termVO=MyTerms.TheInstance.getTerms().get(key);
				TermSummaryPanel termPanel=TermSummaryPanel.getMySummaryPanel(termVO);
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
		TermSummaryPanel termPanel=TermSummaryPanel.getMySummaryPanel(newTerm);
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
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	@Override
	public void onChange() {
		// TODO Auto-generated method stub
		currentIndex=0;
		browseNext();
		TermSuggestionsPanel.getInstance().browseFirst();
	}
}
