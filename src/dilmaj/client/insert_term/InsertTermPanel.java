package dilmaj.client.insert_term;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

import dilmaj.client.MyPanel;
import dilmaj.client.welcome.AllTermsPanel;
import dilmaj.client.welcome.WelcomeController;
import dilmaj.shared.*;

public class InsertTermPanel extends HorizontalPanel implements MyPanel {
	private Label termLabel=new Label(GlobalSettings.constants.term());
	private TextBox termBox=new TextBox();
	private Button insertButton=new Button(GlobalSettings.constants.create());
	private ListBox languageBox=new ListBox();
	//private AllTermsPanel allPanel;
	private InsertTermController controller;
	private MessageComposite messageVO;
	private static InsertTermPanel theInstance=null;
	
	public static InsertTermPanel getInstance() {
		if (theInstance==null)
			theInstance=new InsertTermPanel();
		return theInstance;
	}
	
	private InsertTermPanel() {
		//this.allPanel=allPanel;
		
		controller=new InsertTermController(this);

		add(termLabel);
		add(termBox);
		add(insertButton);
		add(languageBox);
		
		insertButton.addClickHandler(controller);
		
		languageBox.addItem(GlobalSettings.constants.english());
		languageBox.addItem(GlobalSettings.constants.french());
		languageBox.addItem(GlobalSettings.constants.arabic());
		languageBox.setVisibleItemCount(1);
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
		this.messageVO=messageVO;
		Window.alert(messageVO.toString());
	}
}
