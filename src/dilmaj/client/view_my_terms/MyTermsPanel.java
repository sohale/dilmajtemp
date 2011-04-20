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

import dilmaj.client.TermSummaryPanel;
import dilmaj.client.insert_suggestion.InsertSuggestionPanel;
import dilmaj.shared.*;

public class MyTermsPanel extends HorizontalPanel {
	private FlexTable termsTable=new FlexTable();
	private HashMap<Long, TermComposite> allTerms;
	private InsertSuggestionPanel insertPanel;
	
	public MyTermsPanel() {
		add(termsTable);
	}
	
	public void populateTable(HashMap<Long, TermComposite> terms) {
		allTerms=terms;
		
		Iterator<Long> iterator=allTerms.keySet().iterator();
		int row=0;
		while (iterator.hasNext()) {
			Long key=iterator.next();
			TermSummaryPanel termPanel=new TermSummaryPanel(allTerms.get(key), null);
			termsTable.setWidget(row++, 0, termPanel);
		}
	}
	
	public void updateTermsTable(TermComposite newTerm) {
		if (allTerms!=null)
			allTerms=new HashMap<Long, TermComposite>();
		allTerms.put(newTerm.getId(), newTerm);
		TermSummaryPanel termPanel=new TermSummaryPanel(newTerm, null);
		termsTable.setWidget(allTerms.size(), 0, termPanel);
	}
	
	public HashMap<Long, TermComposite> getAllTerms() {
		return allTerms;
	}
	
	public List<TermComposite> getSome(String captionFilter) {
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
	}
}
