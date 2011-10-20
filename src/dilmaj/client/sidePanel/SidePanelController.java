package dilmaj.client.sidePanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;
import dilmaj.client.WaitPanel;
import dilmaj.shared.GlobalSettings;

public class SidePanelController implements ClickHandler {
	private TermServiceAsync termSvc = GWT.create(TermService.class);
	int tsTurn=0;

	public void populateMe(TermsTable source, int from, int to) {
		WaitPanel.getInstance().show();
		
		if (source.name().compareTo("TermSuggestionTable")==0) {
			termSvc.getTermsWithSuggestion(from, to, GetTermSuggestionsCallback.getInstance());
		}
		if (source.name().compareTo("TermOnlyTable")==0) {
			termSvc.getTermsWithoutSuggestion(from, to, GetTermsOnlyCallback.getInstance());
		}
	}

	@Override
	public void onClick(ClickEvent event) {
		try {
			Label label=(Label)event.getSource();
			
			FlexTable flexTable=(FlexTable)label.getParent();
			HorizontalPanel horizontalPanel=(HorizontalPanel)flexTable.getParent();
			if (horizontalPanel.getParent().getClass().getName().compareToIgnoreCase("dilmaj.client.sidePanel.TermSuggestionsPanel")==0) {
				if (label.getText().equals("<<")) {
					if (TermsTable.TermSuggestionTable.isNextEnabled()) {
						++tsTurn;
						populateMe(TermsTable.TermSuggestionTable,tsTurn*GlobalSettings.getTermsPerPage(), (tsTurn+1)*GlobalSettings.getTermsPerPage()-1);
					}
				} else {
					if (TermsTable.TermSuggestionTable.isPrevEnabled()) {
						--tsTurn;
						populateMe(TermsTable.TermSuggestionTable,tsTurn*GlobalSettings.getTermsPerPage(), (tsTurn+1)*GlobalSettings.getTermsPerPage()-1);
					}
				}
			}

			if (horizontalPanel.getParent().getClass().getName().compareToIgnoreCase("dilmaj.client.sidePanel.AllTermsPanel")==0) {
				if (label.getText().equals("<<")) {
					if (TermsTable.TermOnlyTable.isNextEnabled()) {
						++tsTurn;
						populateMe(TermsTable.TermOnlyTable,tsTurn*GlobalSettings.getTermsPerPage(), (tsTurn+1)*GlobalSettings.getTermsPerPage()-1);
					}
				} else {
					if (TermsTable.TermOnlyTable.isPrevEnabled()) {
						--tsTurn;
						populateMe(TermsTable.TermOnlyTable,tsTurn*GlobalSettings.getTermsPerPage(), (tsTurn+1)*GlobalSettings.getTermsPerPage()-1);
					}
				}
			}
		} catch(Exception e) {
			
		}
	}
}
