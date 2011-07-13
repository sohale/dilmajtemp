package dilmaj.client.settings;

import dilmaj.client.DilmajConstants;
import dilmaj.client.top.TopMenu;
import dilmaj.client.view_my_terms.MyTermsPanel;
import dilmaj.client.welcome.AllTermsPanel;
import dilmaj.client.welcome.TermSuggestionsPanel;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.Language;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.SettingsComposite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SettingsPanel extends VerticalPanel {
	private Button closeButton=new Button("x");
	private DilmajConstants constants = GWT.create(DilmajConstants.class);
	private Button okButton=new Button(constants.confirm());
	private SettingsController controller=new SettingsController(this);
	private MessageComposite message;
	private SettingsComposite settingsVO;
	private Label label;
	
	private Label termsLabel;
	private TextBox termsBox;
	
	private ListBox sourceLanguageBox=new ListBox();
	private ListBox targetLanguageBox=new ListBox();

	private static SettingsPanel theInstance=null;
	
	public static SettingsPanel getInstance() {
		if (theInstance==null)
			theInstance=new SettingsPanel();
		
		return theInstance;
	}
	
	private SettingsPanel() {
		closeButton.addClickHandler(controller);
		add(closeButton);
		label=new Label();
		add(label);
		
		termsLabel=new Label("Number of terms in each page");
		add(termsLabel);
		termsBox=new TextBox();
		add(termsBox);
		
		add(sourceLanguageBox);
		add(targetLanguageBox);
		for (String s : Language.getAllLanguages()) {
			sourceLanguageBox.addItem(s);
			targetLanguageBox.addItem(s);
		}
		
		okButton.addClickHandler(controller);
		add(okButton);
	}
	
	public void setMessage(MessageComposite messageVO) {
		message=messageVO;
		Window.alert(message.toString());
	}
	
	public void setSettings(SettingsComposite settings) {
		settingsVO=settings;
		if (settings!=null) {
			termsBox.setText(settings.getTermsPerPage()+"");
			sourceLanguageBox.setSelectedIndex(settings.getSourceLanguage());
			targetLanguageBox.setSelectedIndex(settings.getTargetLanguage());
		} else {
			termsBox.setText("");
			sourceLanguageBox.setSelectedIndex(Language.getLanguageIndex("English"));
			targetLanguageBox.setSelectedIndex(Language.getLanguageIndex("Persian"));
		}
		//TermSuggestionsPanel.getInstance().browseFirst();
		AllTermsPanel.getInstance().browseFirst();
		MyTermsPanel.getInstance().browseFirst();
	}
	
	public SettingsComposite getSettings() {
		return settingsVO;
	}
	
	public void update() {
		int tpp=Integer.parseInt(termsBox.getText());
		int sl=sourceLanguageBox.getSelectedIndex();
		int tl=targetLanguageBox.getSelectedIndex();
		settingsVO.setTermsPerpage(tpp);
		settingsVO.setSourceLanguage(sl);
		settingsVO.setTargetLanguage(tl);
	}
	
	public int getTermsPerPage() {
		if (settingsVO==null)
			return 10;
		return settingsVO.getTermsPerPage();
	}
}
