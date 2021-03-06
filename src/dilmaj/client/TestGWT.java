package dilmaj.client;





import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import com.google.gwt.user.client.ui.RootPanel;
//import com.google.gwt.widgetideas.client.CollapsiblePanel;

import dilmaj.client.insert_suggestion.InsertSuggestionPanel;
import dilmaj.client.insert_term.InsertTermPanel;
import dilmaj.client.livelog.LiveLogPanel;
import dilmaj.client.notice.NoticePanel;
import dilmaj.client.register.FindUserCallback;
import dilmaj.client.search.SearchPanel;
import dilmaj.client.sidePanel.TermSuggestionsPanel;
import dilmaj.client.sidePanel.TermTabsPanel;
import dilmaj.client.top.TopMenu;
import dilmaj.client.top.TopMenuTabs;
import dilmaj.client.users_panel.UsersPanel;
import dilmaj.client.view_suggestion.LoadSuggestionCallback;
import dilmaj.client.view_term.LoadTermCallback;
import dilmaj.client.welcome.LoadAllTermsCallback;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.TermComposite;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestGWT implements EntryPoint {
	private TermServiceAsync termSvc = GWT.create(TermService.class);
	private TermSuggestionServiceAsync termSuggestionSvc = GWT.create(TermSuggestionService.class);

	private DilmajConstants constants = GWT.create(DilmajConstants.class);
	  
	private InsertSuggestionPanel insertSuggestionPanel=InsertSuggestionPanel.getInstance();
	private InsertTermPanel insertTermPanel;

	private DilmajUserServiceAsync userSvc = GWT.create(DilmajUserService.class);

	private TestMysqlServiceAsync testSvc = GWT.create(TestMysqlService.class);
	
	public void onModuleLoad() {
		// this is to test mysql
		/*testSvc.test(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				RootPanel.get("header").add(new Label("call back failed!"));
			}

			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				RootPanel.get("header").add(new Label(result));
			}
			
		});*/
		
		String termId = com.google.gwt.user.client.Window.Location.getParameter("termId");
		String activator = com.google.gwt.user.client.Window.Location.getParameter("activator");
		String username = com.google.gwt.user.client.Window.Location.getParameter("username");
		String termSuggestionId = com.google.gwt.user.client.Window.Location.getParameter("termSuggestionId");
	    
        /*String link = GWT.getModuleBaseURL() + "feed"; 
        RootPanel.get().add(new HTML("<a href=\"" + link + "\">Dilmaj Feed</a>"));
		Date date=new Date();
        RootPanel.get().add(new HTML("<a href=\"" + link + "?date="+date.getTime() +"\">Recent Dilmaj Feed</a>"));*/
        
        //RootPanel.get("noticeThing").add(NoticePanel.getInstance());
        TopMenu.getInstance();
	    //RootPanel.get("navigation").add(TopMenuTabs.getInstance());
		
		if (termSuggestionId!=null) {
			termSuggestionSvc.load(Long.parseLong(termSuggestionId), new LoadSuggestionCallback());
		} else if (termId!=null) {
			TermComposite termVO=new TermComposite();
			termVO.setId(Long.parseLong(termId));
			termSvc.get(termVO, new LoadTermCallback());
		} else {
			if (termId==null && username==null && activator==null) {
				TermSuggestionsPanel termSuggestionsPanel=TermSuggestionsPanel.getInstance();
				termSvc.loadAll(new LoadAllTermsCallback());
			    
				SearchPanel searchPanel=SearchPanel.getInstance();
				//RootPanel.get("search").add(searchPanel);
				
				//RegisterPanel registerPanel=new RegisterPanel();
				//RootPanel.get("registerationPanel").add(registerPanel);
				
				//RootPanel.get("termSuggestions").add(termSuggestionsPanel);
				
				//RootPanel.get("myTerms").add(MyTermsPanel.getInstance());
				
			    insertTermPanel=InsertTermPanel.getInstance();
			    //RootPanel.get("insertTerm").add(insertTermPanel);
			    
			    //RootPanel.get("insertSuggestion").add(insertSuggestionPanel);
			    insertSuggestionPanel.setVisible(false);
			    
			    /*HorizontalPanel hp = new HorizontalPanel();
			    HTML html = new HTML("<p>This is html with a <a href='AllTerms.html'>link</a></p>");
			    hp.add(html);
			    Label label=new Label(GWT.getModuleBaseURL()+"AllTerms.html");*/
			    //RootPanel.get("allTerms").add(allTermsPanel);
			    
			    HorizontalPanel descriptionPanel=new HorizontalPanel();
			    descriptionPanel.setWidth("300px");
			    descriptionPanel.add(new Label(constants.describeProject()));
			    //RootPanel.get("projectDescription").add(descriptionPanel);
			    
			    //RootPanel.get("liveLogWall").add(LiveLogPanel.getInstance());
			    
			    /*RootPanel.get("termsTable").add(*/TermTabsPanel.getInstance();//);
			    
			   // RootPanel.get("usersPanel").add(UsersPanel.getInstance());
			} else if (username!=null && activator!=null) {
				MemberComposite userVO=new MemberComposite();
				userVO.setActivator(activator);
				userVO.setUsername(username);
				userSvc.find(userVO, new FindUserCallback(this));
			} else { // URL with termId
				//ViewTermPanel viewTermPanel=new ViewTermPanel(termId);
			    //RootPanel.get("insertTerm").add(viewTermPanel);
			}
		}
	}
	
	public void setMessage(MessageComposite messageComposite) {
		// TODO Auto-generated method stub
		Window.alert(messageComposite.toString());
	}
}
