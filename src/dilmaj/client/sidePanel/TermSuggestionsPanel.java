package dilmaj.client.sidePanel;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TermSuggestionsPanel extends VerticalPanel {
	private Image nextButton=new Image("images/leftArrow.jpg");
	private Image prevButton=new Image("images/rightArrow.jpg");
	private Image nextButtonTop=new Image("images/leftArrow.jpg");
	private Image prevButtonTop=new Image("images/rightArrow.jpg");
	
	private static TermSuggestionsPanel theInstance=null;
	
	public static TermSuggestionsPanel getInstance() {
		if (theInstance==null)
			theInstance=new TermSuggestionsPanel();
		return theInstance;
	}
	
	private TermSuggestionsPanel() {
		nextButton.setAltText(">>");
		prevButton.setAltText("<<");
		nextButtonTop.setAltText(">>");
		prevButtonTop.setAltText("<<");
		
		setStyleName("termsTable");

		//HorizontalPanel navigationPanel=new HorizontalPanel();
		//navigationPanel.setStyleName("navigationTable");
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
		//navigationPanelTop.setStyleName("navigationTable");
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
		//add(tsTable);
		add(TermsTable.TermSuggestionTable.getTermsTable());
		add(navigationTable);
		
		SidePanelController controller=new SidePanelController();
		nextButton.addClickHandler(controller);
		prevButton.addClickHandler(controller);
		nextButtonTop.addClickHandler(controller);
		prevButtonTop.addClickHandler(controller);
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
	
	/*public void browseFirst() {
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
		//		rows.add(tableRow);
				//row++;
			//}
	//	}
		
		//AllTermsPanel.getInstance().browseFirst();
	//}
}
