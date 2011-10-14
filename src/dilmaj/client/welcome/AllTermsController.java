package dilmaj.client.welcome;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

import dilmaj.client.TermButton;
import dilmaj.shared.Controller;

public class AllTermsController extends Controller {
	AllTermsPanel panel;
	
	public AllTermsController(AllTermsPanel tsPanel) {
		panel=tsPanel;
	}
	
	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		Label button=(Label)event.getSource();
		String text=button.getText();
		
		if (text.compareTo("next")==0)
			panel.browseNext();
		else
			panel.browsePrev();
	}
}
