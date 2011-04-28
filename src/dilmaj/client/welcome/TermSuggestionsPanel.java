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

import dilmaj.client.TermButton;
import dilmaj.client.insert_suggestion.InsertSuggestionPanel;
import dilmaj.shared.*;

public class TermSuggestionsPanel extends HorizontalPanel {
	private FlexTable tsTable=new FlexTable();
	//private HashMap<Long,TermComposite> allTerms;
	private Set<TermComposite> termSet=new HashSet<TermComposite>();
	//private WelcomeController controller;
	private AllTermsPanel allTermsPanel;
	
	private static TermSuggestionsPanel theInstance=null;
	
	public static TermSuggestionsPanel getInstance() {
		if (theInstance==null)
			theInstance=new TermSuggestionsPanel();
		return theInstance;
	}
	
	private TermSuggestionsPanel() {
		add(tsTable);
	}
	
	public void populateTable() {
		//this.allTerms=allTerms;
		
		Iterator<Long> iterator=AllTerms.TheInstance.getTerms().keySet().iterator();
		int row=0;
		while (iterator.hasNext()) {
			Long key=iterator.next();
			TermComposite termVO=AllTerms.TheInstance.getTerms().get(key);
			
			Iterator<TermSuggestionComposite> tsIterator=termVO.getSuggestions().iterator();
			if (tsIterator.hasNext()) {
				TermButton termButton=new TermButton(termVO);
				tsTable.setWidget(row, 0, termButton);
				
				int col=1;
				while (tsIterator.hasNext()) {
					TermComposite suggestionVO=tsIterator.next().getSuggestion();
					TermButton suggestionButton=new TermButton(suggestionVO);
					tsTable.setWidget(row, col, suggestionButton);
					col++;
				}
				row++;
			}
		}
	}
}
