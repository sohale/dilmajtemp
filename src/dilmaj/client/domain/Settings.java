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
    
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    protected Long id;

    public Settings(SettingsComposite settingsVO) {
		username=settingsVO.getUser().getUsername();
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
}
