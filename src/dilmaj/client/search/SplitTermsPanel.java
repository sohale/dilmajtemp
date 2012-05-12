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
	SearchPanel parent;
	//PriorityQueue<Integer> maxHeap=new PriorityQueue<Integer>(10, this);
	
	private static SplitTermsPanel theInstance=null;
	
	public static SplitTermsPanel getInstance(SearchPanel panel) {
		if (theInstance==null)
			theInstance=new SplitTermsPanel(panel);
		
		return theInstance;
	}
	
	private SplitTermsPanel(SearchPanel panel) {
		parent=panel;
		//setGlassEnabled(true);
		setStyleName("transparentPopup");
		//mainPanel.setStyleName("transparentPopup");
		add(mainPanel);
		closeButton.addClickHandler(this);
		mainPanel.add(closeButton);
		mainPanel.setHorizontalAlignment(VerticalPanel.ALIGN_LEFT);
		//setWidth("100px");
	}
	
	public void addButton(Label button) {
		mainPanel.add(button);
		button.addClickHandler(this);
		++nButtons;
		if (nButtons==1) {
	//		maxHeap.add(button.getOffsetWidth());
			int x=parent.getAbsoluteLeft();
			int y=parent.getAbsoluteTop();
			int h=parent.getOffsetHeight();
			setPopupPosition(x, y+h);
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
		parent.reset();
	}

	@Override
	public int compare(Integer x, Integer y) {
		// TODO Auto-generated method stub
		return y-x;
	}
}
