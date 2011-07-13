package dilmaj.client.domain;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.PersistenceCapable;

import dilmaj.shared.SettingsComposite;

@PersistenceCapable
public class Settings {
    @Persistent
	String username;
    
    @Persistent
	int sourceLanguage=0;
    
    @Persistent
	int targetLanguage=0;
    
    @Persistent
    int termsPerPage; // indicates how many terms a user wishes to see in every page, the actual value would be 128+termsPerPage, range:{-128,127}, default is 10
    
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    protected Long id;

    public Settings(SettingsComposite settingsVO) {
		username=settingsVO.getUser();
		termsPerPage=settingsVO.getTermsPerPage();
		sourceLanguage=settingsVO.getSourceLanguage();
		targetLanguage=settingsVO.getTargetLanguage();
	}
	
    public Settings() {
	}

	public void setId(Long id) {
    	this.id=id;
    }
    
	public Long getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username=username;
	}

	public int getTermsPerPage() {
		return termsPerPage;
	}

	public void setTermsPerPage(int termsPerPage2) {
		// TODO Auto-generated method stub
		termsPerPage=termsPerPage2;
	}

	public int getSourceLanguage() {
		// TODO Auto-generated method stub
		return sourceLanguage;
	}

	public int getTargetLanguage() {
		// TODO Auto-generated method stub
		return targetLanguage;
	}
}
