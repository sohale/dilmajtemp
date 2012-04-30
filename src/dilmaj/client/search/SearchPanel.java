package dilmaj.client.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

import dilmaj.shared.GlobalSettings;

public class SearchPanel {
	private HorizontalPanel inputPanel=new HorizontalPanel();
	private HorizontalPanel mainPanel=new HorizontalPanel();
	private SplitTermsPanel outputPanel=SplitTermsPanel.getInstance(this);
	private Button searchLabel=new Button(GlobalSettings.constants.search());
	private TextBox inputBox=new TextBox();//(TextBox)RootPanel.get("searchInput").asWidget();//new TextBox();
	
	private SearchController controller;
	
	private List<Label> buttons=new ArrayList<Label>();
	
	private Label theButton=new Label();
	
	//private FlexTable searchTable=new FlexTable();
	
	private static SearchPanel theInstance=null;
	
	public static SearchPanel getInstance() {
		if (theInstance==null)
			theInstance=new SearchPanel();
		
		return theInstance;
	}
	
	private SearchPanel() {
//		/setStyleName("search");
		controller=new SearchController(this);
		//setStyleName("searchTable");
		inputBox.setStyleName("searchInput");
		inputBox.addKeyDownHandler(controller);
		inputPanel.setStyleName("searchBox");
		RootPanel.get("search").add(inputPanel);
		RootPanel.get("search").add(searchLabel);
		//inputBox.setWidth(GlobalSettings.getBrowserWidth()*.4+"px");
		//searchTable.setWidget(0, 0, searchLabel);
		//searchTable.setWidget(0, 1, inputBox);
		
		//add(searchTable);
		//RootPanel.get("searchBox").add(inputBox);
		theButton.setStyleName("termSplitButton");
		theButton.addClickHandler(controller);
		searchLabel.setStyleName("searchLabel");
		inputPanel.add(inputBox);
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

	public int getAbsoluteLeft() {
		return RootPanel.get("search").getAbsoluteLeft();
	}

	public int getAbsoluteTop() {
		return RootPanel.get("search").getAbsoluteTop();
	}

	public int getOffsetHeight() {
		// TODO Auto-generated method stub
		return RootPanel.get("search").getOffsetHeight();
	}

}
