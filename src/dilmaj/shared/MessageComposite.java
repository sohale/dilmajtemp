package dilmaj.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MessageComposite implements IsSerializable {
	String message;
	Date date;
	
	public MessageComposite() {
		
	}
	
	public MessageComposite(String message) {
		this.message=message;
	}
	
	public String toString() {
		return message;
	}

	public void setDateTime(Date date) {
		this.date=date;
	}
	
	public Date getDateTime() {
		return date;
	}
}
