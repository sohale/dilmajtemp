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
	protected int sourceLanguage;
	protected int targetLanguage;
	
	public SettingsComposite() {
		
	}
	
	public SettingsComposite(Settings settings) {
		id=settings.getId();
		termsPerPage=settings.getTermsPerPage();
		username=settings.getUsername();
		
		Integer sl, tl;
		sl=settings.getSourceLanguage();
		tl=settings.getTargetLanguage();
		
		if (sl==null)
			sourceLanguage=Language.ENGLISH.indexOf();
		else
			sourceLanguage=settings.getSourceLanguage();
		
		if (tl==null)
			targetLanguage=Language.PERSIAN.indexOf();
		else
			targetLanguage=settings.getTargetLanguage();
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

	public int getSourceLanguage() {
		// TODO Auto-generated method stub
		return sourceLanguage;
	}

	public int getTargetLanguage() {
		// TODO Auto-generated method stub
		return targetLanguage;
	}

	public void setSourceLanguage(int sl) {
		// TODO Auto-generated method stub
		sourceLanguage=sl;
	}

	public void setTargetLanguage(int tl) {
		// TODO Auto-generated method stub
		targetLanguage=tl;
	}
}
