package dilmaj.client.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

import dilmaj.shared.GlobalSettings;

public class SearchPanel extends VerticalPanel {
	private HorizontalPanel inputPanel=new HorizontalPanel();
	private SplitTermsPanel outputPanel=SplitTermsPanel.getInstance(this);
	private Label searchLabel=new Label(GlobalSettings.constants.search()+":");
	private TextBox inputBox=new TextBox();
	
	private SearchController controller;
	
	private List<Label> buttons=new ArrayList<Label>();
	
	private Label theButton=new Label();
	
	private FlexTable searchTable=new FlexTable();
	
	private static SearchPanel theInstance=null;
	
	public static SearchPanel getInstance() {
		if (theInstance==null)
			theInstance=new SearchPanel();
		
		return theInstance;
	}
	
	private SearchPanel() {
		controller=new SearchController(this);
		setStyleName("searchTable");
		inputBox.addKeyDownHandler(controller);
		inputPanel.add(inputBox);
		inputBox.setWidth(GlobalSettings.getBrowserWidth()*.4+"px");
		searchTable.setWidget(0, 0, searchLabel);
		searchTable.setWidget(0, 1, inputBox);
		
		add(searchTable);
		theButton.setStyleName("termSplitButton");
		theButton.addClickHandler(controller);
	}
	
	public void addButton(String entry) {
		outputPanel.add(new Button(entry));
	}
	
	public void setButtons(String[] entries) {
		String text=inputBox.getText();
		if (text.length()>0) {
			theButton.setText(inputBox.getText());
			outputPanel.addButton(theButton);
		} else
			outputPanel.removeButton(theButton);
		
		Iterator<Label> iterator=buttons.iterator();
		while (iterator.hasNext()) {
			outputPanel.removeButton(iterator.next());
		}
		buttons.clear();
		
		if (text.length()>0 && entries.length>1)
			for (String s:entries) {
			//for (int i=0;i<entries.length;i++) {
				Label button=new Label(s);
				button.setStyleName("termSplitButton");
				button.addClickHandler(controller);
				outputPanel.addButton(button);
				buttons.add(button);
			}
	}
	
	public String getInputText() {
		return inputBox.getText();
	}

	public void reset() {
		inputBox.setText("");
		buttons.clear();
	}

	public void setSearchFilter(String string) {
		// TODO Auto-generated method stub
		inputBox.setText(string);
	}
	
	public TextBox getSearchBox() {
		return inputBox;
	}
}
