package dilmaj.client.sidePanel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

import dilmaj.client.WaitPanel;
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
	private boolean isPrevEnabled;
	private boolean isNextEnabled;
	
	private TermsTable() {
		from=0;
		to=GlobalSettings.getTermsPerPage()-1;
		//index=0;
		//actualTo=0;
		theTable.setWidth(""+GlobalSettings.getBrowserWidth()*GlobalSettings.getTermsPanelRatio()+"px");
		theTable.setHeight(""+GlobalSettings.getBrowserHeight()*GlobalSettings.getTermsPanelHeightRatio()+"px");
		controller.populateMe(this, from, to);
	}
	
	public boolean isPrevEnabled() {
		return isPrevEnabled;
	}
	
	public boolean isNextEnabled() {
		return isNextEnabled;
	}
	
	public void populate(List<TermComposite> newTerms) {
		int n=newTerms.size()-1;
		TermComposite nextTerms=newTerms.get(n);
		long next=nextTerms.getId();
		newTerms.remove(n);
		if (from==0)
			isPrevEnabled=false;
		else
			isPrevEnabled=true;
		if (next==0)
			isNextEnabled=false;
		else
			isNextEnabled=true;
		
		if (theTable.getParent().getClass().getName().equals("dilmaj.client.sidePanel.TermSuggestionsPanel")) {
			TermSuggestionsPanel parent=(TermSuggestionsPanel)theTable.getParent();
			parent.setNextLabel(next);
			parent.setPrevLabel(from);
		}
		
		if (theTable.getParent().getClass().getName().equals("dilmaj.client.sidePanel.AllTermsPanel")) {
			AllTermsPanel parent=(AllTermsPanel)theTable.getParent();
			parent.setNextLabel(next);
			parent.setPrevLabel(from);
		}
		
		if (newTerms.size()>0) {
			loadedTerms.clear();
			loadedTerms.addAll(newTerms);
			
			browse();
		}
	}
	
	public void browse() {
		int row=0;
		Iterator<TermComposite> iterator=loadedTerms.iterator();
		theTable.clear();
			
		for (;iterator.hasNext();) {
			TermComposite termVO=iterator.next();
			TermSummaryPanel widget=TermSummaryPanel.getSummaryPanel(termVO);
			theTable.setWidget(row, 0, widget);
			theTable.getRowFormatter().setStyleName(row, "termTable");
			row++;
		}

		WaitPanel.getInstance().hide();
	}
	
	/*public int getLocalIndex() {
		return index;
	}
	
	public boolean endOfRows() {
		if (index==actualTo+1)
			return true;
		return false;
	}*/
	
	public FlexTable getTermsTable() {
		return theTable;
	}
}
