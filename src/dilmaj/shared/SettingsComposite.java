package dilmaj.shared;

import java.sql.Timestamp;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

import dilmaj.client.domain.Settings;

public class SettingsComposite implements IsSerializable {
	protected MemberComposite theUser;
	protected Long id;
	
	public SettingsComposite(Settings settings) {
		id=settings.getId();
	}
	
	public void setMember(MemberComposite memberVO) {
		theUser=memberVO;
	}
	
	public MemberComposite getUser() {
		return theUser;
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}
}
