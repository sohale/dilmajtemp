package dilmaj.shared;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserComposite implements IsSerializable {
	protected List<EntryComposite> entries=new ArrayList<EntryComposite>();
	protected Long id;
	
	public UserComposite() {
		
	}
	
	public List<EntryComposite> getEntries() {
		return entries;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public Long getId() {
		return id;
	}
}
