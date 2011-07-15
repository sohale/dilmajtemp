package dilmaj.client.insert_suggestion;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

import dilmaj.client.SettingsListener;
import dilmaj.client.settings.SettingsPanel;
import dilmaj.shared.*;

public class InsertSuggestionPanel extends HorizontalPanel implements SettingsListener {
	private Label termLabel=new Label(GlobalSettings.constants.term());
	private Label equalLabel=new Label("=");
	private Label selectedTerm=new Label("");
	private TextBox suggestionBox=new TextBox();
	private Button insertButton=new Button(GlobalSettings.constants.create());
	private ListBox languageBox=new ListBox();
	private MessageComposite message;
	
	private TermComposite term;
	
	private InsertSuggestionController controller;
	
	private static InsertSuggestionPanel theInstance=null;
	
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
		
		for (Language l : Language.values()) {
			languageBox.addItem(l.toString());
		}

		languageBox.setVisibleItemCount(1);
		languageBox.setSelectedIndex(SettingsPanel.getInstance().getTargetLanguage());
		
		SettingsPanel.getInstance().addChangeListener(this);
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
	
	@Override
	public void onChange() {
		// TODO Auto-generated method stub
		languageBox.setSelectedIndex(SettingsPanel.getInstance().getTargetLanguage());
	}

	public int getLanguage() {
		// TODO Auto-generated method stub
		return languageBox.getSelectedIndex();
	}
}
