package dilmaj.client.termPanel;

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
	//Controller controller;
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
		
		if (termVO.getRunningTitle()!=null) {
			String[] suggestions=termVO.getRunningTitle().split("-");
			int n=suggestions.length;
			StringBuilder sb=new StringBuilder();
			sb.append("("+(n-1)+")"+" ");
			//Iterator<TermSuggestionComposite> tsIterator=termVO.getSuggestions().iterator();
			for (int i=1;i<3 && i<n;i++) {
				//TermSuggestionComposite tsVO=tsIterator.next();
				sb.append(suggestions[i]);
				sb.append("/");
			}
			if (n>3)
				sb.append("... ");
			int lang=termVO.getLanguage();
			if (lang!=Language.UNKNOWN.indexOf())
				sb.append(Language.getLanguage(termVO.getLanguage()));
			title=sb.toString();
		} else {
			title="(0)";
		}
		
		summaryLabel=new Label(title);
		contents.setWidget(0,1,summaryLabel);
		contents.getCellFormatter().setStyleName(0, 1, "termSummaryTrailer");
	}

	public void setButtonStyle(String styleName) {
		button.setNewStyle(styleName);
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
