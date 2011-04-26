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
	private InsertSuggestionPanel insertPanel;
	
	public MyTermsPanel() {
		add(termsTable);
		MyTerms.TheInstance.setMyPanel(this);
	}
	
	public void populateTable() {
		Iterator<Long> iterator=MyTerms.TheInstance.getTerms().keySet().iterator();
		int row=0;
		while (iterator.hasNext()) {
			Long key=iterator.next();
			TermSummaryPanel termPanel=new TermSummaryPanel(MyTerms.TheInstance.getTerms().get(key), null);
			termsTable.setWidget(row++, 0, termPanel);
		}
	}
	
	public void updateTermsTable(TermComposite newTerm) {
		TermSummaryPanel termPanel=new TermSummaryPanel(newTerm, null);
		termsTable.setWidget(MyTerms.TheInstance.getTerms().size(), 0, termPanel);
	}
}
