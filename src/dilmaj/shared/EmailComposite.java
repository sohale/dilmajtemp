package dilmaj.shared;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class EmailComposite implements IsSerializable {
	//protected List<EntryComposite> entries=new ArrayList<EntryComposite>();
	private String subject;
	private String from;
	private String to;
	private String contents; 
	
	public EmailComposite() {
		
	}
	
	public EmailComposite(String to) {
		this.to=to;
	}
	
	public EmailComposite(String subject, String to, String contents) {
		this.subject=subject;
		this.to=to;
		this.contents=contents;
	}
	
	public String getTo() {
		return to;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getBody() {
		return contents;
	}
}
