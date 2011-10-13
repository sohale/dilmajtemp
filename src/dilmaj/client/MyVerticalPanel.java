package dilmaj.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MyVerticalPanel extends HorizontalPanel implements ClickHandler {
	VerticalPanel tabBar=new VerticalPanel();
	
	Map<String, CellPanel> contents=new HashMap<String, CellPanel>();
	
	CellPanel selectedPanel;
	
	public MyVerticalPanel() {
		add(tabBar);
	}
	
	public void add(String barLabel, CellPanel barPanel) {
		CellPanel cellPanel=contents.get(barLabel);
		
		if (cellPanel==null) {
			Label label=new Label(barLabel);
			tabBar.add(label);
			contents.put(barLabel, barPanel);
			barPanel.setVisible(false);
			barPanel.setStyleName("selectedTab");
			label.addClickHandler(this);
			add(barPanel);
		}
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		for (int i=0;i<tabBar.getWidgetCount();i++)
			tabBar.getWidget(i).removeStyleName("selectedTab");
		
		Label label=(Label)event.getSource();
		CellPanel cellPanel=contents.get(label.getText());
		cellPanel.setVisible(true);
		if (selectedPanel!=null)
			selectedPanel.setVisible(false);
		selectedPanel=cellPanel;
		label.setStyleName("selectedTab");
	}
	
	public void select(String label) {
		CellPanel cellPanel=contents.get(label);
		cellPanel.setVisible(true);
		if (selectedPanel!=null)
			selectedPanel.setVisible(false);
		selectedPanel=cellPanel;
	}
}
