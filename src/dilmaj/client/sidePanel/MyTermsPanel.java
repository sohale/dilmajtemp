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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import dilmaj.client.SettingsListener;
import dilmaj.client.insert_suggestion.InsertSuggestionPanel;
import dilmaj.client.settings.SettingsPanel;
import dilmaj.client.termPanel.TermSummaryController;
import dilmaj.client.termPanel.TermSummaryPanel;
import dilmaj.client.view_my_terms.MyTerms;
import dilmaj.client.view_my_terms.MyTermsController;
import dilmaj.shared.*;

public class MyTermsPanel extends VerticalPanel {
	private Label nextButton=new Label("<<");
	private Label prevButton=new Label(">>");
	private Label nextButtonTop=new Label("<<");
	private Label prevButtonTop=new Label(">>");
	private static MyTermsPanel theInstance=null;
	
	private boolean isLoggedIn=false;
	
	public static MyTermsPanel getInstance() {
		if (theInstance==null)
			theInstance=new MyTermsPanel();
		
		return theInstance;
	}
	
	private MyTermsPanel() {
		HorizontalPanel navigationPanel=new HorizontalPanel();
		navigationPanel.setStyleName("navigationTable");
		FlexTable navigationTable=new FlexTable();
		navigationTable.setStyleName("navigationTable");
		navigationPanel.add(navigationTable);
		navigationTable.setWidget(0, 1, prevButton);
		navigationTable.setWidget(0, 0, nextButton);
		navigationTable.getColumnFormatter().setStyleName(0, "prevButton");
		navigationTable.getColumnFormatter().setStyleName(1, "nextButton");
		prevButton.setStyleName("termButton");
		nextButton.setStyleName("termButton");
		
		HorizontalPanel navigationPanelTop=new HorizontalPanel();
		navigationPanelTop.setStyleName("navigationTable");
		FlexTable navigationTableTop=new FlexTable();
		navigationTableTop.setStyleName("navigationTable");
		navigationPanelTop.add(navigationTableTop);
		navigationTableTop.setWidget(0, 1, prevButtonTop);
		navigationTableTop.setWidget(0, 0, nextButtonTop);
		navigationTableTop.getColumnFormatter().setStyleName(0, "prevButton");
		navigationTableTop.getColumnFormatter().setStyleName(1, "nextButton");
		prevButtonTop.setStyleName("termButton");
		nextButtonTop.setStyleName("termButton");
		
		add(navigationPanelTop);
		//add(termsTable);
		add(TermsTable.MyTermsTable.getTermsTable());
		add(navigationPanel);

		SidePanelController controller=new SidePanelController();
		nextButton.addClickHandler(controller);
		prevButton.addClickHandler(controller);
		nextButtonTop.addClickHandler(controller);
		prevButtonTop.addClickHandler(controller);
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
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
