package dilmaj.client.search;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SplitTermsPanel extends PopupPanel implements ClickHandler, Comparator<Integer> {
	VerticalPanel mainPanel=new VerticalPanel();
	int nButtons=0;
	Button closeButton=new Button("x");
	Panel parent;
	//PriorityQueue<Integer> maxHeap=new PriorityQueue<Integer>(10, this);
	
	public SplitTermsPanel(Panel panel) {
		parent=panel;
		//setGlassEnabled(true);
		setStyleName("transparentPopup");
		//mainPanel.setStyleName("transparentPopup");
		add(mainPanel);
		closeButton.addClickHandler(this);
		mainPanel.add(closeButton);
		mainPanel.setHorizontalAlignment(VerticalPanel.ALIGN_LEFT);
		setWidth("100px");
	}
	
	public void addButton(Label button) {
		mainPanel.add(button);
		++nButtons;
		if (nButtons==1) {
	//		maxHeap.add(button.getOffsetWidth());
			int x=parent.getAbsoluteLeft();
			int y=parent.getAbsoluteTop();
			setPopupPosition(x, y);
		//	setWidth(maxHeap.peek()+"px");
			show();
		}
	}
	
	public void removeButton(Label label) {
		mainPanel.remove(label);
		//if (button.getOffsetWidth()==maxHeap.peek())
			//maxHeap.poll();
		//setWidth(maxHeap.peek()+"px");
		--nButtons;
		if (nButtons==0)
			hide();
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		hide();
		mainPanel.clear();
		nButtons=0;
	}

	@Override
	public int compare(Integer x, Integer y) {
		// TODO Auto-generated method stub
		return y-x;
	}
}
