package dilmaj.client.search;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;
import dilmaj.client.sidePanel.AllTermsPanel;
import dilmaj.shared.Controller;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.TermComposite;

public class SearchController extends Controller implements KeyDownHandler, ClickHandler {
	private SearchPanel panel;
	private boolean atEnd=true;
	//private AllTermsPanel allPanel;
	private TermServiceAsync termSvc = GWT.create(TermService.class);
	
	public SearchController(SearchPanel searchPanel) {
		panel=searchPanel;
		//this.allPanel=allPanel;
	}
	
	/*@Override
	public void onKeyUp(KeyUpEvent event) {
		// TODO Auto-generated method stub
		String input=panel.getInputText();
		input=input.trim();
		String[] subInputs=input.split(" ");
		panel.setButtons(subInputs);
	}*/

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		Object source=event.getSource();
		String className=source.getClass().getName();
		
		if (className.equals(TextBox.class.getName())) {
			panel.reset();
		} else if (className.equals(Label.class.getName())) {
			Label button=(Label)event.getSource();
			String captionFilter=button.getText();
			termSvc.getSome(captionFilter, new GetSomeTermsCallback(captionFilter));
		}
	}

	@Override
	public void onKeyDown(KeyDownEvent event) {
		// TODO Auto-generated method stub
		if (event.getNativeKeyCode()==KeyCodes.KEY_ENTER) {
			TextBox eventBox=(TextBox)event.getSource();
			String captionFilter=eventBox.getText();
			termSvc.getSome(captionFilter, new GetSomeTermsCallback(captionFilter));
		}
	}
}
