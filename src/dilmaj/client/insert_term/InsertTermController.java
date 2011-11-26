package dilmaj.client.insert_term;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.PopupPanel;

import dilmaj.client.MyPanel;
import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;
import dilmaj.shared.Controller;
import dilmaj.shared.TermComposite;

public class InsertTermController extends Controller implements ClickHandler {
	private TermServiceAsync termSvc = GWT.create(TermService.class);
	private MyPanel insertTermPanel;
	
	public InsertTermController(MyPanel insertPanel) {
		insertTermPanel=insertPanel;
	}
	
	public void insertTerm(String caption, int language) {
		TermComposite termVO=new TermComposite(caption, language);
		
		termSvc.addEntry(termVO, new InsertTermCallback(insertTermPanel));
	}
	
	public void insertTerm(String caption, String username) {
		TermComposite termVO=new TermComposite(caption, username);
		
		termSvc.addEntry(termVO, new InsertTermCallback(insertTermPanel));
	}
	
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		String caption=((InsertTermPanel)insertTermPanel).getTermCaption();
		int language=((InsertTermPanel)insertTermPanel).getTermLanguage();
		
		PopupPanel popup=InsertTermPanel.getInstance().getPopupParent();
		if (popup!=null)
			popup.hide();
		
		insertTerm(caption, language);
	}
}
