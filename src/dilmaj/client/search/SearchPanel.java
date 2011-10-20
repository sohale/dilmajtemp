package dilmaj.client.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

import dilmaj.client.sidePanel.AllTermsPanel;

public class SearchPanel extends VerticalPanel {
	private HorizontalPanel inputPanel=new HorizontalPanel();
	private HorizontalPanel outputPanel=new HorizontalPanel();
	
	private TextBox inputBox=new TextBox();
	
	private SearchController controller;
	
	private List<Button> buttons=new ArrayList<Button>();
	
	private Button theButton=new Button();
	
	public SearchPanel() {
		controller=new SearchController(this);
		
		inputBox.addKeyUpHandler(controller);
		inputPanel.add(inputBox);
		add(inputPanel);
		
		add(outputPanel);
		
		add(theButton);
		theButton.addClickHandler(controller);
		theButton.setVisible(false);
	}
	
	public void addButton(String entry) {
		outputPanel.add(new Button(entry));
	}
	
	public void setButtons(String[] entries) {
		String text=inputBox.getText();
		if (text.length()>0) {
			theButton.setText(inputBox.getText());
			theButton.setVisible(true);
		} else
			theButton.setVisible(false);
		
		Iterator<Button> iterator=buttons.iterator();
		while (iterator.hasNext()) {
			outputPanel.remove(iterator.next());
		}
		buttons.clear();
		
		if (text.length()>0 && entries.length>1)
			for (String s:entries) {
			//for (int i=0;i<entries.length;i++) {
				Button button=new Button(s);
				button.addClickHandler(controller);
				outputPanel.add(button);
				buttons.add(button);
			}
	}
	
	public String getInputText() {
		return inputBox.getText();
	}
}
