package dilmaj.client.livelog;

import java.util.List;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import dilmaj.shared.MessageComposite;

public class LiveLogPanel extends ScrollPanel {
	private static LiveLogPanel theInstance=null;
	private static String sessionID;
	private LiveLogController controller;
	private Button refreshButton=new Button("Refresh");
	private int maxMessageId=-1;
	private VerticalPanel mainPanel=new VerticalPanel();
	
	public static LiveLogPanel getInstance() {
		if (theInstance==null)
			theInstance=new LiveLogPanel();
		
		return theInstance;
	}
	
	private LiveLogPanel() {
		add(mainPanel);
		
		setSize("400px", "120px");
		
		controller=new LiveLogController();
		controller.openSession();
		mainPanel.add(refreshButton);
		refreshButton.addClickHandler(controller);
	}

	public void addMessages(List<MessageComposite> newMessages) {
		for (MessageComposite aMessage:newMessages) {
			int messageId=aMessage.getSequence();
			if (maxMessageId<messageId)
				maxMessageId=messageId;
			mainPanel.add(new Label(aMessage.toString()));
		}
		
		scrollToBottom();
	}
	
	public void setSessionID(String result) {
		sessionID=result;
	}
	
	@Override
	public void finalize() throws Throwable {
		try {
			controller.closeSession(sessionID);
		} finally {
			super.finalize();
		}
	}
	
	public int getLastMessageId() {
		return maxMessageId;
	}

	public void setMaxMessageId(Integer result) {
		maxMessageId=result;
	}
}
