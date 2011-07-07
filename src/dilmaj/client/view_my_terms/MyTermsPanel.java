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

import dilmaj.client.TermSummaryController;
import dilmaj.client.TermSummaryPanel;
import dilmaj.client.insert_suggestion.InsertSuggestionPanel;
import dilmaj.shared.*;

public class MyTermsPanel extends VerticalPanel {
	private FlexTable termsTable=new FlexTable();
	private InsertSuggestionPanel insertPanel;
	private int maxLength;
	
	public MyTermsPanel() {
		add(termsTable);
		MyTerms.TheInstance.setMyPanel(this);
	}
	
	public void populateTable() {
		if (MyTerms.TheInstance.getTerms()!=null) {
			Window.alert("My Terms Loaded : "+MyTerms.TheInstance.getTerms().size());
			Iterator<Long> iterator=MyTerms.TheInstance.getTerms().keySet().iterator();
			int row=0;
			while (iterator.hasNext()) {
				Long key=iterator.next();
				TermComposite termVO=MyTerms.TheInstance.getTerms().get(key);
				TermSummaryPanel termPanel=TermSummaryPanel.getSummaryPanel(termVO);
				termsTable.setWidget(row++, 0, termPanel);
			}
		} else {
			termsTable.removeAllRows();
		}
	}
	
	public void updateTermsTable(TermComposite newTerm) {
		TermSummaryPanel termPanel=TermSummaryPanel.getSummaryPanel(newTerm);
		termsTable.setWidget(MyTerms.TheInstance.getTerms().size(), 0, termPanel);
	}
}
