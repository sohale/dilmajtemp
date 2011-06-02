package dilmaj.client.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.PersistenceCapable;

import dilmaj.shared.EntryComposite;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.UserComposite;

@PersistenceCapable
public class User {
    @Persistent
	String username;
    
    @Persistent
	String password;
    
    @Persistent
	String activator=null;
    
    @Persistent
	String name;
    
    @Persistent
	String email;
	
    @Persistent
    List<Long> entries=new ArrayList<Long>();
    
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    protected Long id;

    public User(UserComposite newUser) {
    	try {
    		MemberComposite member=(MemberComposite)newUser;
    		
    		username=member.getUsername();
    		password=member.getPassword();
    		email=member.getEmail();
    		name=member.getName();
    		
    		entries.clear();
    		Iterator<EntryComposite> iterator=member.getEntries().iterator();
    		while (iterator.hasNext()) {
    			entries.add(iterator.next().getId());
    		}
    	} catch (ClassCastException cce) {
    		
    	}
	}
	
    public void setId(Long id) {
    	this.id=id;
    }
    
	public Long getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setActivator(String activator) {
		this.activator=activator;
	}
	
	public String getActivator() {
		return activator;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setUsername(String username) {
		this.username=username;
	}
}
