package dilmaj.client.insert_term;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

import dilmaj.client.DilmajUserService;
import dilmaj.client.DilmajUserServiceAsync;
import dilmaj.client.MyPanel;
import dilmaj.client.TermButton;
import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;
import dilmaj.client.top.GetLoggedAccountCallback;
import dilmaj.client.welcome.AllTermsPanel;
import dilmaj.shared.Controller;
import dilmaj.shared.TermComposite;

public class InsertTermController extends Controller implements ClickHandler {
	private TermServiceAsync termSvc = GWT.create(TermService.class);
	private DilmajUserServiceAsync userSvc = GWT.create(DilmajUserService.class);
	private MyPanel insertTermPanel;
	private AllTermsPanel allPanel;
	
	public InsertTermController(MyPanel insertPanel, AllTermsPanel allPanel) {
		insertTermPanel=insertPanel;
		this.allPanel=allPanel;
	}
	
	public void insertTerm(String caption) {
		TermComposite termVO=new TermComposite(caption);
		
		termSvc.addEntry(termVO, new InsertTermCallback(insertTermPanel, allPanel));
	}
	
	public void insertTerm(String caption, String username) {
		TermComposite termVO=new TermComposite(caption, username);
		
		termSvc.addEntry(termVO, new InsertTermCallback(insertTermPanel, allPanel));
	}
	
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		//insertTermPanel=(MyHorizontalPanel)().getParent();
		//userSvc.getLoggedUser(new GetLoggedAccountCallback((InsertTermPanel)insertTermPanel, this, ((InsertTermPanel)insertTermPanel).getTermCaption()));
		insertTerm(((InsertTermPanel)insertTermPanel).getTermCaption());
		/*TermComposite termVO=insertTerm();*/
	}
}
