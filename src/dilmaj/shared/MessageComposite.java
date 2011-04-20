package dilmaj.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MessageComposite implements IsSerializable {
	String message;
	
	public MessageComposite() {
		
	}
	
	public MessageComposite(String message) {
		this.message=message;
	}
	
	public String toString() {
		return message;
	}
}
