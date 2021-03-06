package dilmaj.client.sidePanel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

import dilmaj.client.DilmajConstants;
import dilmaj.client.WaitPanel;
import dilmaj.client.login.LoginController;
import dilmaj.client.login.LoginListener;
import dilmaj.client.termPanel.TermSummaryPanel;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.TermComposite;

public enum TermsTable implements LoginListener {
	TermSuggestionTable() {
	},
	
	TermOnlyTable() {
		
	},
	
	MyTermsTable() {
		
	};
	
	private DilmajConstants constants = GWT.create(DilmajConstants.class);
	
	@Override
	public void onLogin() {
		loggedIn=true;
		if (name().equals("MyTermsTable"))
			controller.populateMe(this, from, to);
	}
	
	@Override
	public void onLogout() {
		loggedIn=false;
		if (name().equals("MyTermsTable"))
			theTable.clear();
	}
	
	private FlexTable theTable=new FlexTable();
	private boolean loggedIn=false;
	private SidePanelController controller=new SidePanelController();
	private int from, to;
	private List<TermComposite> loadedTerms=new ArrayList<TermComposite>();
	private boolean isPrevEnabled;
	private boolean isNextEnabled;
	
	private TermsTable() {
		from=0;
		to=GlobalSettings.getTermsPerPage()-1;
		/*theTable.setWidth(""+GlobalSettings.getBrowserWidth()*GlobalSettings.getTermsPanelRatio()+"px");
		theTable.setHeight(""+GlobalSettings.getBrowserHeight()*GlobalSettings.getTermsPanelHeightRatio()+"px");*/
		//theTable.setStyleName("termsTable");
	
		LoginController.getInstance().addLoginListener(this);
		
		if (!name().equals("MyTermsTable") || loggedIn)
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
		TermComposite prevTerms=newTerms.get(n);
		long prev=prevTerms.getId();
		newTerms.remove(n);
		--n;
		TermComposite nextTerms=newTerms.get(n);
		long next=nextTerms.getId();
		newTerms.remove(n);
		if (prev==0)
			isPrevEnabled=false;
		else
			isPrevEnabled=true;
		if (next==0)
			isNextEnabled=false;
		else
			isNextEnabled=true;
		
		if (theTable.getParent()!=null) {
			if (theTable.getParent().getClass().getName().equals("dilmaj.client.sidePanel.TermSuggestionsPanel")) {
				TermSuggestionsPanel parent=(TermSuggestionsPanel)theTable.getParent();
				parent.setNextLabel(next);
				parent.setPrevLabel(prev);
			}
			
			if (theTable.getParent().getClass().getName().equals("dilmaj.client.sidePanel.AllTermsPanel")) {
				AllTermsPanel parent=(AllTermsPanel)theTable.getParent();
				parent.setNextLabel(next);
				parent.setPrevLabel(prev);
			}
			
			if (theTable.getParent().getClass().getName().equals("dilmaj.client.sidePanel.MyTermsPanel")) {
				MyTermsPanel parent=(MyTermsPanel)theTable.getParent();
				parent.setNextLabel(next);
				parent.setPrevLabel(prev);
			}
		}
		
		if (newTerms.size()>0) {
			loadedTerms.clear();
			loadedTerms.addAll(newTerms);
		}
		
		browse();

		if (name().equals("MyTermsTable"))
			WaitPanel.getInstance().removeMessage(constants.myTermsBeingLoaded());
		if (name().equals("TermOnlyTable"))
			WaitPanel.getInstance().removeMessage(constants.allTermsBeingLoaded());
		if (name().equals("TermSuggestionTable"))
			WaitPanel.getInstance().removeMessage(constants.termSuggestionsBeingLoaded());
	}
	
	public void browse() {
		int row=0;
		Iterator<TermComposite> iterator=loadedTerms.iterator();
		theTable.clear();
			
		for (;iterator.hasNext();) {
			TermComposite termVO=iterator.next();
			TermSummaryPanel widget=TermSummaryPanel.getSummaryPanel(termVO);
			theTable.setWidget(row, 0, widget);
			theTable.getRowFormatter().setStyleName(row, "termsTableData");
			row++;
		}
	}
	
	public FlexTable getTermsTable() {
		return theTable;
	}
}
