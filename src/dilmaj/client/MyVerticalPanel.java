package dilmaj.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MyVerticalPanel extends HorizontalPanel implements ClickHandler {
	VerticalPanel tabBar=new VerticalPanel();
	
	Map<String, CellPanel> contents=new HashMap<String, CellPanel>();
	
	CellPanel selectedPanel;
	Label selectedLab=null;
	
	public MyVerticalPanel() {
		add(tabBar);
	}
	
	public void add(String barLabel, CellPanel barPanel) {
		CellPanel cellPanel=contents.get(barLabel);
		
		if (cellPanel==null) {
			Label label=new Label(barLabel);
			label.setStyleName("tabLabel");
			tabBar.add(label);
			contents.put(barLabel, barPanel);
			//barPanel.setVisible(false);
			barPanel.setStyleName("tabPanel");
			label.addClickHandler(this);
			//add(barPanel);
		}
	}
	
	public void remove(String barLabel) {
		for (int i=0;i<tabBar.getWidgetCount();i++) {
			Widget widget=tabBar.getWidget(i);
			if (widget.getClass().equals(Label.class)) {
				Label lab=(Label)widget;
				if (lab.getText().compareTo(barLabel)==0)
					tabBar.remove(lab);
			}
		}
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if (selectedLab!=null) {
			selectedLab.setStyleName("tabLabel");
		}
				
		Label label=(Label)event.getSource();
		label.setStyleName("selectedTabLabel");
		selectedLab=label;
		CellPanel cellPanel=contents.get(label.getText());
		//cellPanel.setVisible(true);
		RootPanel.get("termsPanel").add(cellPanel);
		if (selectedPanel!=null)
			RootPanel.get("termsPanel").remove(selectedPanel);
		selectedPanel=cellPanel;
	}
	
	public void select(String label) {
		if (selectedLab!=null) {
			selectedLab.setStyleName("tabLabel");
			selectedLab=null;
		}

		for (int i=0;selectedLab==null && i<tabBar.getWidgetCount();i++) {
			Widget widget=tabBar.getWidget(i);
			if (widget.getClass().equals(Label.class)) {
				Label lab=(Label)widget;
				if (lab.getText().compareTo(label)==0) {
					selectedLab=lab;
					lab.setStyleName("selectedTabLabel");
				}
			}
		}
		
		CellPanel cellPanel=contents.get(label);
		//cellPanel.setVisible(true);
		RootPanel.get("termsPanel").add(cellPanel);
		if (selectedPanel!=null)
			RootPanel.get("termsPanel").remove(selectedPanel);
		selectedPanel=cellPanel;
	}
}
