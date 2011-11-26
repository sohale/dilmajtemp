package dilmaj.client.insert_term;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;

import dilmaj.client.MyPanel;
import dilmaj.client.SettingsListener;
import dilmaj.client.settings.SettingsPanel;
import dilmaj.shared.*;

public class InsertTermPanel extends HorizontalPanel implements MyPanel, SettingsListener {
	private Label termLabel=new Label(GlobalSettings.constants.term());
	private TextBox termBox=new TextBox();
	private Button insertButton=new Button(GlobalSettings.constants.create());
	private ListBox languageBox=new ListBox();
	private InsertTermController controller;
	private MessageComposite messageComposite;
	private static InsertTermPanel theInstance=null;
	private PopupPanel popupParent=null;
	
	public static InsertTermPanel getInstance() {
		if (theInstance==null)
			theInstance=new InsertTermPanel();
		return theInstance;
	}
	
	private InsertTermPanel() {
		//this.allPanel=allPanel;
		
		controller=new InsertTermController(this);

		setStyleName("insertTermPanel");
		add(languageBox);
		add(insertButton);
		add(termBox);
		add(termLabel);
		
		insertButton.addClickHandler(controller);
		
		for (Language l : Language.getIterable()) {
			languageBox.addItem(l.toString());
		}

		languageBox.setVisibleItemCount(1);
		languageBox.setSelectedIndex(SettingsPanel.getInstance().getSourceLanguage());
		
		SettingsPanel.getInstance().addChangeListener(this);
	}
	
	public String getTermCaption() {
		return termBox.getText();
	}
	
	public void setTermCaption(String caption) {
		termBox.setText(caption);
	}

	@Override
	public void setMessage(MessageComposite messageVO) {
		// TODO Auto-generated method stub
		messageComposite=messageVO;
		Window.alert(messageComposite.toString());
	}
	
	@Override
	public void onChange() {
		// TODO Auto-generated method stub
		languageBox.setSelectedIndex(SettingsPanel.getInstance().getSourceLanguage());
	}

	public int getTermLanguage() {
		// TODO Auto-generated method stub
		return languageBox.getSelectedIndex();
	}
	
	public void setPopupParent(PopupPanel popupPanel) {
		popupParent=popupPanel;
	}
	
	public PopupPanel getPopupParent() {
		return popupParent;
	}
}
