package dilmaj.shared;

import java.sql.Timestamp;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

import dilmaj.client.domain.Settings;

public class SettingsComposite implements IsSerializable {
	protected MemberComposite theUser;
	protected Long id;
	protected byte termsPerPage;
	
	public SettingsComposite() {
		
	}
	
	public SettingsComposite(Settings settings) {
		id=settings.getId();
		termsPerPage=settings.getTermsPerPage();
	}
	
	public void setMember(MemberComposite memberVO) {
		theUser=memberVO;
	}
	
	public MemberComposite getUser() {
		return theUser;
	}

	public Long getId() {
		return id;
	}

	public byte getTermsPerPage() {
		return termsPerPage;
	}
}
