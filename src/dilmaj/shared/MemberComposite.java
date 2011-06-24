package dilmaj.shared;

import java.util.HashMap;

import dilmaj.client.domain.User;

public class MemberComposite extends UserComposite {
	protected String username;
	protected String password;
	protected String email;
	protected String name;
	protected String adminPin;
	protected String activator;
	protected SettingsComposite settings;
	
	private static HashMap<String, MemberComposite> allMembers=new HashMap<String, MemberComposite>();
	
	public static MemberComposite getInstance(User theUser) {
		MemberComposite memberVO=allMembers.get(theUser.getUsername());
		if (memberVO==null) {
			memberVO=new MemberComposite(theUser);
			allMembers.put(theUser.getUsername(), memberVO);
		}
		return memberVO;
	}
	
	public MemberComposite() {
		
	}
	
	private MemberComposite(User newUser) {
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
		this.username=username2;
	}
	
	public void setSettings(SettingsComposite settingsVO) {
		settings=settingsVO;
	}

	public SettingsComposite getSettings() {
		// TODO Auto-generated method stub
		return settings;
	}
}
