package dilmaj.client;

import java.util.HashMap;
import java.util.Iterator;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import dilmaj.client.login.LoginController;
import dilmaj.client.login.LoginListener;
import dilmaj.shared.Controller;
import dilmaj.shared.TermComposite;
import dilmaj.shared.Language;
import dilmaj.shared.TermSuggestionComposite;

public class TermSummaryPanel extends HorizontalPanel implements LoginListener {
	TermComposite termVO;
	TermButton button;
	Controller controller;
	Label summaryLabel;
	String title;
	
	private static HashMap<TermComposite, TermSummaryPanel> summaryPanels=new HashMap<TermComposite, TermSummaryPanel>();
	private static HashMap<TermComposite, TermSummaryPanel> mySummaryPanels=new HashMap<TermComposite, TermSummaryPanel>();
	
	public static TermSummaryPanel getSummaryPanel(TermComposite termVO) {
		TermSummaryPanel summaryPanel=summaryPanels.get(termVO);
		if (summaryPanel==null) {
			summaryPanel=new TermSummaryPanel(termVO, TermSummaryController.getController(termVO));
			summaryPanels.put(termVO, summaryPanel);
		}
		return summaryPanel;
	}
	
	public static TermSummaryPanel getMySummaryPanel(TermComposite termVO) {
		TermSummaryPanel summaryPanel=mySummaryPanels.get(termVO);
		if (summaryPanel==null) {
			summaryPanel=new TermSummaryPanel(termVO, TermSummaryController.getController(termVO));
			mySummaryPanels.put(termVO, summaryPanel);
		}
		return summaryPanel;
	}
	
	private TermSummaryPanel(TermComposite termVO, TermSummaryController controller) {
		LoginController.getInstance().addLoginListener(this);
		
		FlexTable contents=new FlexTable();
		add(contents);		
		
		this.termVO=termVO;
		button=new TermButton(termVO);
		//String trail="";
		String caption=termVO.getCaption();
		button.setText(caption);
		button.setTitle(termVO.getRunningTitle());
		button.addClickHandler(controller);
		contents.setWidget(0,0,button);
		
		//title="("+GlobalSettings.constants.suggestion()+" "+termVO.getSuggestions().size()+")";
		StringBuilder sb=new StringBuilder();
		sb.append("("+termVO.getSuggestions().size()+")"+" ");
		Iterator<TermSuggestionComposite> tsIterator=termVO.getSuggestions().iterator();
		for (int i=0;i<2 && tsIterator.hasNext();i++) {
			TermSuggestionComposite tsVO=tsIterator.next();
			sb.append(tsVO.getSuggestion().getCaption()+"/");
		}
		sb.append("... ");
		sb.append(Language.getLanguage(termVO.getLanguage()));
		title=sb.toString();
		
		summaryLabel=new Label(title);
		contents.setWidget(0,1,summaryLabel);
		contents.getCellFormatter().setStyleName(0, 1, "termSummaryTrailer");
	}

	@Override
	public void onLogin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLogout() {
		// TODO Auto-generated method stub
		mySummaryPanels.clear();
	}
}
