package dilmaj.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import dilmaj.client.view_term.ViewTermController;
import dilmaj.shared.Controller;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.TermComposite;

public class TermSummaryPanel extends HorizontalPanel {
	TermComposite termVO;
	TermButton button;
	Controller controller;
	Label summaryLabel;
	String title;
	
	public TermSummaryPanel(TermComposite termVO, Controller controller) {
		this.termVO=termVO;
		button=new TermButton(termVO);
		button.setText(termVO.getCaption());
		button.setTitle(termVO.getId()+"");
		button.addClickHandler(controller);
		add(button);
		
		title="("+GlobalSettings.constants.suggestion()+" "+termVO.getSuggestions().size()+")";
		summaryLabel=new Label(title);
		add(summaryLabel);
	}
}
