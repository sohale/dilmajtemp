package dilmaj.shared;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.IsSerializable;

import dilmaj.client.domain.Settings;

public class SettingsComposite implements IsSerializable {
	protected String username;
	protected Long id;
	protected int termsPerPage;
	
	public SettingsComposite() {
		
	}
	
	public SettingsComposite(Settings settings) {
		id=settings.getId();
		termsPerPage=settings.getTermsPerPage();
		username=settings.getUsername();
	}
	
	public void setUsername(String username) {
		this.username=username;
	}
	
	public String getUser() {
		return username;
	}

	public Long getId() {
		return id;
	}

	public int getTermsPerPage() {
		return termsPerPage;
	}

	public void setTermsPerpage(int parseInt) {
		termsPerPage=parseInt;
	}
}
