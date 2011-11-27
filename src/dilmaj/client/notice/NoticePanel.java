package dilmaj.client.notice;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NoticePanel extends VerticalPanel {
	Image noticeButton=new Image("images/notice.jpg");
	
	private static NoticePanel theInstance=null;
	
	public static NoticePanel getInstance() {
		if (theInstance==null)
			theInstance=new NoticePanel();
		
		return theInstance;
	}
	
	private NoticePanel() {
		add(noticeButton);
	}
}
