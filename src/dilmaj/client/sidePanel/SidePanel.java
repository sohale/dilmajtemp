package dilmaj.client.sidePanel;

import java.util.HashMap;
import java.util.Map;

import dilmaj.shared.GlobalSettings;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SidePanel /*extends VerticalPanel*/ implements ClickHandler {
	HorizontalPanel tabBar=new HorizontalPanel();
	
	Map<String, CellPanel> contents=new HashMap<String, CellPanel>();
	
	CellPanel selectedPanel;
	Label selectedLab=null;
	
	public SidePanel() {
		//tabBar.setStyleName("terms");
		//RootPanel.get("terms").add(tabBar);
	}
	
	public void add(String barLabel, CellPanel barPanel) {
		CellPanel cellPanel=contents.get(barLabel);
		
		if (cellPanel==null) {
			HorizontalPanel panel=new HorizontalPanel();
			panel.setStyleName("termMenu");
			Label label=new Label(barLabel);
			label.setStyleName("termMenuLabel");
			//label.setWidth(""+GlobalSettings.getBrowserWidth()*GlobalSettings.getSideMenuRatio()+"px");
			panel.add(label);
			//barPanel.setWidth(""+GlobalSettings.getBrowserWidth()*GlobalSettings.getTermsPanelRatio()+"px");
			RootPanel.get("terms").add(panel);
			contents.put(barLabel, barPanel);
			barPanel.setStyleName("termMenuLabel");
			label.addClickHandler(this);
			//RootPanel.get("termsTable").add(barPanel);
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
		Label label=(Label)event.getSource();
		HorizontalPanel panel=(HorizontalPanel)label.getParent();
		
		if (label!=selectedLab) {
			if (selectedLab!=null) {
				HorizontalPanel selectedPan=(HorizontalPanel)selectedLab.getParent();
				selectedPan.setStyleName("termMenu");
			}
		
			panel.setStyleName("selectedTab");
			selectedLab=label;
			CellPanel cellPanel=contents.get(label.getText());
			//cellPanel.setVisible(true);

			if (selectedPanel!=null)
				RootPanel.get("terms").remove(selectedPanel);
			RootPanel.get("terms").add(cellPanel);
			selectedPanel=cellPanel;
		}
	}
	
	public void select(String label) {
		if (selectedLab!=null) {
			HorizontalPanel selectedPan=(HorizontalPanel)selectedLab.getParent();
			selectedPan.setStyleName("termMenu");
			selectedLab=null;
		}

		for (int i=0;selectedLab==null && i<tabBar.getWidgetCount();i++) {
			Widget widget=tabBar.getWidget(i);
			if (widget.getClass().equals(Label.class)) {
				Label lab=(Label)widget;
				if (lab.getText().compareTo(label)==0) {
					selectedLab=lab;
					HorizontalPanel pan=(HorizontalPanel)lab.getParent();
					pan.setStyleName("selectedTab");
				}
			}
		}
		
		CellPanel cellPanel=contents.get(label);
		//cellPanel.setVisible(true);
		RootPanel.get("terms").add(cellPanel);
		if (selectedPanel!=null)
			RootPanel.get("terms").remove(selectedPanel);
		selectedPanel=cellPanel;
	}
}
