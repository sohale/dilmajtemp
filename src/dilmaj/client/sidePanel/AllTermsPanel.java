package dilmaj.client.sidePanel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import dilmaj.client.MyPanel;
import dilmaj.client.SettingsListener;
import dilmaj.client.insert_suggestion.InsertSuggestionPanel;
import dilmaj.client.settings.SettingsPanel;
import dilmaj.client.termPanel.TermSummaryPanel;
import dilmaj.client.welcome.AllTerms;
import dilmaj.client.welcome.AllTermsController;
import dilmaj.shared.*;

public class AllTermsPanel extends VerticalPanel implements MyPanel {
	private Image nextButton=new Image("images/leftArrow.jpg");
	private Image prevButton=new Image("images/rightArrow.jpg");
	private Image nextButtonTop=new Image("images/leftArrow.jpg");
	private Image prevButtonTop=new Image("images/rightArrow.jpg");
	
	private static AllTermsPanel theInstance=null;
	
	public static AllTermsPanel getInstance() {
		if (theInstance==null)
			theInstance=new AllTermsPanel();
		
		return theInstance;
	}
	
	private AllTermsPanel() {
		setStyleName("termsTable");
		//HorizontalPanel navigationPanel=new HorizontalPanel();
		//navigationPanel.setStyleName("termsTableHeader");
		FlexTable navigationTable=new FlexTable();
		navigationTable.setStyleName("termsTableHeader");
		//navigationPanel.add(navigationTable);
		navigationTable.setWidget(0, 1, prevButton);
		navigationTable.setWidget(0, 0, nextButton);
		navigationTable.getColumnFormatter().setStyleName(0, "termsTableHeaderRightArrow");
		navigationTable.getColumnFormatter().setStyleName(1, "termsTableHeaderLeftArrow");
		prevButton.setStyleName("termsTableHeaderRightArrow");
		nextButton.setStyleName("termsTableHeaderLeftArrow");
		
		//HorizontalPanel navigationPanelTop=new HorizontalPanel();
		///navigationPanelTop.setStyleName("termsTableHeader");
		FlexTable navigationTableTop=new FlexTable();
		navigationTableTop.setStyleName("termsTableHeader");
		//navigationPanelTop.add(navigationTableTop);
		navigationTableTop.setWidget(0, 1, prevButtonTop);
		navigationTableTop.setWidget(0, 0, nextButtonTop);
		navigationTableTop.getColumnFormatter().setStyleName(0, "termsTableHeaderRightArrow");
		navigationTableTop.getColumnFormatter().setStyleName(1, "termsTableHeaderLeftArrow");
		prevButtonTop.setStyleName("termsTableHeaderRightArrow");
		nextButtonTop.setStyleName("termsTableHeaderLeftArrow");
		
		add(navigationTableTop);
		//add(termsTable);
		add(TermsTable.TermOnlyTable.getTermsTable());
		add(navigationTable);
		
		SidePanelController controller=new SidePanelController();
		nextButton.addClickHandler(controller);
		prevButton.addClickHandler(controller);
		nextButtonTop.addClickHandler(controller);
		prevButtonTop.addClickHandler(controller);
		//AllTerms.TheInstance.setAllTermsPanel(this);
		
		//SettingsPanel.getInstance().addChangeListener(this);
	}
	
/*	public void browseFirst() {
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
	}*/

	@Override
	public void setMessage(MessageComposite messageVO) {
		// TODO Auto-generated method stub
		
	}

	public void setNextLabel(long next) {
		// TODO Auto-generated method stub
		nextButton.setTitle(next+"");
		nextButtonTop.setTitle(next+"");
	}

	public void setPrevLabel(long prev) {
		// TODO Auto-generated method stub
		prevButton.setTitle(prev+"");
		prevButtonTop.setTitle(prev+"");
	}
}
