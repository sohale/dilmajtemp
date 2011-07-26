package dilmaj.client.livelog;

import java.util.List;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import dilmaj.shared.MessageComposite;

public class LiveLogPanel extends VerticalPanel {
	private static LiveLogPanel theInstance=null;
	private static String sessionID;
	private LiveLogController controller;
	
	public LiveLogPanel getInstance() {
		if (theInstance==null)
			theInstance=new LiveLogPanel();
		
		return theInstance;
	}
	
	private LiveLogPanel() {
		controller=new LiveLogController(this);
		controller.openSession();
	}

	public void addMessages(List<MessageComposite> newMessages) {
		for (MessageComposite aMessage:newMessages)
			add(new Label(aMessage.toString()));
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
}
