package dilmaj.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MessageComposite implements IsSerializable {
	String message;
	Long sequence;
	
	public MessageComposite() {
		
	}
	
	public MessageComposite(String message) {
		this.message=message;
	}
	
	public String toString() {
		return message;
	}

	public void setSequence(long sequence) {
		this.sequence=sequence;
	}
	
	public long getSequence() {
		return sequence;
	}
}
