package dilmaj.shared;


import java.sql.Timestamp;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class EntryComposite implements IsSerializable {
	protected Date timeStamp;
	protected Long id;
	protected String username;
	
	public Date getTimeStamp() {
		return timeStamp;
	}
	
	public EntryComposite() {
		
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setUser(String username) {
		this.username=username;
	}
	
	public String getUser() {
		return username;
	}
	
	public void setTimestamp(Date timeStamp) {
		this.timeStamp=timeStamp;
	}
}
