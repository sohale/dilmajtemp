package dilmaj.client;





import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import com.google.gwt.user.client.ui.RootPanel;
//import com.google.gwt.widgetideas.client.CollapsiblePanel;

import dilmaj.client.insert_suggestion.InsertSuggestionPanel;
import dilmaj.client.insert_term.InsertTermPanel;
import dilmaj.client.register.FindUserCallback;
import dilmaj.client.register.RegisterPanel;
import dilmaj.client.register.SendConfirmationEmailCallback;
import dilmaj.client.search.SearchPanel;
import dilmaj.client.top.TopMenu;
import dilmaj.client.view_my_terms.MyTermsPanel;
import dilmaj.client.view_term.ViewTermPanel;
import dilmaj.client.welcome.AllTermsPanel;
import dilmaj.client.welcome.LoadAllTermsCallback;
import dilmaj.client.welcome.TermSuggestionsPanel;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.Repository;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestGWT implements EntryPoint {
	private TermServiceAsync termSvc = GWT.create(TermService.class);

	private DilmajConstants constants = GWT.create(DilmajConstants.class);
	  
	  private String username=new String("");
	  private long userid;
	  
	  private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private InsertSuggestionPanel insertSuggestionPanel=InsertSuggestionPanel.getInstance();
	private AllTermsPanel allTermsPanel;
	private InsertTermPanel insertTermPanel;

	private DilmajUserServiceAsync userSvc = GWT.create(DilmajUserService.class);
	
	private TopMenu topMenu=new TopMenu();

	public void onModuleLoad() {
		String termId = com.google.gwt.user.client.Window.Location.getParameter("termId");
		String activator = com.google.gwt.user.client.Window.Location.getParameter("activator");
		String username = com.google.gwt.user.client.Window.Location.getParameter("username");
		
		RootPanel.get("topMenu").add(topMenu);
		
		if (termId==null && username==null && activator==null) {
			TermSuggestionsPanel termSuggestionsPanel=TermSuggestionsPanel.getInstance();
			termSvc.loadAll(new LoadAllTermsCallback());
		    
			SearchPanel searchPanel=new SearchPanel();
			RootPanel.get("searchPanel").add(searchPanel);
			
			RegisterPanel registerPanel=new RegisterPanel();
			RootPanel.get("registerationPanel").add(registerPanel);
			
			RootPanel.get("termSuggestions").add(termSuggestionsPanel);
			
			RootPanel.get("myTerms").add(new MyTermsPanel());
			
		    allTermsPanel=new AllTermsPanel(insertSuggestionPanel, termSuggestionsPanel);
		    insertTermPanel=InsertTermPanel.getInstance();
		    RootPanel.get("insertTerm").add(insertTermPanel);
		    
		    RootPanel.get("insertSuggestion").add(insertSuggestionPanel);
		    
		    RootPanel.get("allTerms").add(allTermsPanel);
		    
		    HorizontalPanel descriptionPanel=new HorizontalPanel();
		    descriptionPanel.setWidth("300px");
		    descriptionPanel.add(new Label(constants.describeProject()));
		    RootPanel.get("projectDescription").add(descriptionPanel);
		} else if (username!=null && activator!=null) {
			MemberComposite userVO=new MemberComposite();
			userVO.setActivator(activator);
			userVO.setUsername(username);
			userSvc.find(userVO, new FindUserCallback(this));
		} else {
			ViewTermPanel viewTermPanel=new ViewTermPanel(termId);
		    RootPanel.get("insertTerm").add(viewTermPanel);
		}
	}

	public void setMessage(MessageComposite messageComposite) {
		// TODO Auto-generated method stub
		Window.alert(messageComposite.toString());
	}
}
