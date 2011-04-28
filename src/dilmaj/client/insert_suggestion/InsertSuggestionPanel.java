package dilmaj.client.insert_suggestion;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

import dilmaj.client.welcome.WelcomeController;
import dilmaj.shared.*;

public class InsertSuggestionPanel extends HorizontalPanel {
	private Label termLabel=new Label(GlobalSettings.constants.term());
	private Label equalLabel=new Label("=");
	private Label selectedTerm=new Label("");
	private TextBox suggestionBox=new TextBox();
	private Button insertButton=new Button(GlobalSettings.constants.create());
	private ListBox languageBox=new ListBox();
	private MessageComposite message;
	
	private TermComposite term;
	private TermComposite suggestion;
	
	private InsertSuggestionController controller;
	
	private static InsertSuggestionPanel theInstance;
	
	public static InsertSuggestionPanel getInstance() {
		if (theInstance==null)
			theInstance=new InsertSuggestionPanel();
		return theInstance;
	}
	
	private InsertSuggestionPanel() {
		if (controller==null) {
			controller=new InsertSuggestionController();
		}

		add(termLabel);
		add(selectedTerm);
		add(equalLabel);
		add(suggestionBox);
		add(insertButton);
		add(languageBox);
		
		insertButton.addClickHandler(controller);
		
		languageBox.addItem(GlobalSettings.constants.english());
		languageBox.addItem(GlobalSettings.constants.french());
		languageBox.addItem(GlobalSettings.constants.arabic());
		languageBox.setVisibleItemCount(1);
	}
	
	public String getSuggestionCaption() {
		return suggestionBox.getText();
	}
	
	public void setTerm(TermComposite term) {
		this.term=term;
		selectedTerm.setText(term.getCaption());
	}
	
	public TermComposite getTerm() {
		return term;
	}
	
	public void setMessage(MessageComposite messageVO) {
		message=messageVO;
		Window.alert(message.toString());
	}
	
	public InsertSuggestionController getController() {
		return controller;
	}
}
