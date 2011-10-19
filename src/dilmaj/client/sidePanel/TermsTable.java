package dilmaj.client.sidePanel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

import dilmaj.client.termPanel.TermSummaryPanel;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.TermComposite;

public enum TermsTable {
	TermSuggestionTable() {
	},
	
	TermOnlyTable() {
		
	},
	
	MyTermsTable() {
		
	};
	
	private FlexTable theTable=new FlexTable();
	private boolean isLoaded=false;
	private SidePanelController controller=new SidePanelController();
	private int from, to, index, actualTo;
	private List<TermComposite> loadedTerms=new ArrayList<TermComposite>();
	
	private TermsTable() {
		from=0;
		to=GlobalSettings.getTermsPerLoad()-1;
		index=0;
		actualTo=0;
		theTable.setWidth(""+GlobalSettings.getBrowserWidth()*GlobalSettings.getTermsPanelRatio()+"px");
		theTable.setHeight(""+GlobalSettings.getBrowserHeight()*GlobalSettings.getTermsPanelHeightRatio()+"px");
		controller.populateMe(this, from, to);
	}
	
	public void populate(List<TermComposite> newTerms) {
		loadedTerms.clear();
		loadedTerms.addAll(newTerms);
		actualTo=newTerms.size()-1+from;
		
		index=0;
		
		browse();
	}
	
	public void browse() {
		int i=0;
		int row=0;
		Iterator<TermComposite> iterator=loadedTerms.iterator();
		while (iterator.hasNext() && i<index) {
			iterator.next();
			i++;
		}
			
		for (;i<GlobalSettings.getTermsPerPage() && iterator.hasNext();i++) {
			TermComposite termVO=iterator.next();
			TermSummaryPanel widget=TermSummaryPanel.getSummaryPanel(termVO);
			theTable.setWidget(row, 0, widget);
			theTable.getRowFormatter().setStyleName(row, "termTable");
			row++;
			index++;
		}
	}
	
	public int getLocalIndex() {
		return index;
	}
	
	public boolean endOfRows() {
		if (index==actualTo+1)
			return true;
		return false;
	}
	
	public FlexTable getTermsTable() {
		return theTable;
	}
}
