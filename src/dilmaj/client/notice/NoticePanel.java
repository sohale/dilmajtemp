package dilmaj.client.notice;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.xml.client.Document;

public class NoticePanel extends HorizontalPanel {
	Image noticeButton=new Image("images/nonotice.jpg");
	NoticeController controller=new NoticeController();
	Document theFeed;
	Label noticeLabel=new Label("0");
	boolean noticeShown=false;
	
	private static NoticePanel theInstance=null;
	
	public static NoticePanel getInstance() {
		if (theInstance==null)
			theInstance=new NoticePanel();
		
		return theInstance;
	}
	
	private NoticePanel() {
		noticeButton.setAltText("nonotice");
		add(noticeButton);
		noticeButton.addClickHandler(controller);
		//add(noticeLabel);
		noticeLabel.setStyleName("noticeLabel");
		timer.scheduleRepeating(1000);
		//addClickHandler();
		//noticeLabel.addClickHandler(controller);
		//FlexTable theTable=new FlexTable();
		//add(noticeButton);
		//noticeLabel.setStyleName("noticeLabel");
		//add(noticeLabel);
		//noticeLabel.setWordWrap(true);
		//theTable.setText(0, 0, noticeLabel.getText());
		//theTable.setText(0, 0, "0");
		//theTable.setWidget(1, 1, noticeButton);
		//theTable.getCellFormatter().setVerticalAlignment(0, 0, ALIGN_TOP);
		//theTable.getCellFormatter().setHorizontalAlignment(0, 0, ALIGN_LEFT);

		//theTable.setHeight("5px");
		//theTable.setWidth("5px");
		//theTable.addStyleName("noticeTable");
		//theTable.getCellFormatter().setStyleName(0, 0, "noticeLabel");
		
		///add(theTable);
	}

	public void setNewFeed(Document xmlDocument) {
		// TODO Auto-generated method stub
		theFeed=xmlDocument;
		int count=xmlDocument.getElementsByTagName("item").getLength();
		noticeLabel.setText(count+"");
		if (count>0  && !noticeShown) {
			add(noticeLabel);
			noticeShown=true;
			noticeButton.setUrl("images/notice.jpg");
			noticeButton.setAltText("notice");		}
	}
		
	Timer timer=new Timer() {
		public void run() {
			controller.readFeed();
		}
	};
}
