package dilmaj.client.users_panel;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class UsersPanel extends VerticalPanel {
	private Map<String, Label> users=new HashMap<String, Label>();
	
	private static UsersPanel theInstance=null;
	
	private boolean isLoggedIn=false; 
	
	public static UsersPanel getInstance() {
		if (theInstance==null)
			theInstance=new UsersPanel();
		return theInstance;
	}
	
	private UsersPanel() {
		
	}
	
	public void addUser(String username) {
		Label label=new Label(username);
		add(label);
		
		users.put(username, label);
	}
	
	public void updateUsersList(Set<String> result) {
		// TODO Auto-generated method stub
		/*for (String username:users.keySet()) {
			if (!result.contains(username)) {
				Label label=users.get(username);
				remove(label);
				users.remove(username);
			}
		}
		for (String username:result) {
			if (!users.containsKey(username))
				addUser(username);
		}*/
		users.clear();
		
		for (String username:result) {
			//addUser(username);
			users.put(username, null);
		}
	}

	public void empty() {
		clear();
		users.clear();
	}
	
	Timer timer=new Timer() {
		public void run() {
			clear();
			
			for (String username:users.keySet()) {
				Label label=users.get(username);
				if (label==null) {
					label=new Label(username);
					users.put(username, label);
				}
				add(label);
			}
		}
	};
	
	public void logIn() {
		isLoggedIn=true;
		timer.scheduleRepeating(1000);
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	
	public void logOut() {
		empty();
		isLoggedIn=false;
		timer.cancel();
	}
}
