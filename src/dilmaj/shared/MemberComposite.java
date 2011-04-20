package dilmaj.shared;

import dilmaj.client.domain.User;

public class MemberComposite extends UserComposite {
	protected String username;
	protected String password;
	protected String email;
	protected String name;
	protected String adminPin;
	protected String activator;
	
	public MemberComposite() {
		
	}
	
	public MemberComposite(User newUser) {
		this.id=newUser.getId();
		this.username=newUser.getUsername();
		this.password=newUser.getPassword();
		this.email=newUser.getEmail();
	}
	
	public MemberComposite(String username2, String password2, String adminPin2,
			String email2, String name2) {
		// TODO Auto-generated constructor stub
		super();
		
		username=username2;
		password=password2;
		adminPin=adminPin2;
		email=email2;
		name=name2;
	}

	public MemberComposite(String username, String password) {
		super();
		
		this.username=username;
		this.password=password;
	}
	

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setActivator(String activator) {
		this.activator=activator;
	}
	
	public String getActivator() {
		return activator;
	}

	public void setUsername(String username2) {
		// TODO Auto-generated method stub
		this.username=username2;
	}
}
