package dilmaj.client.domain;

//import java.sql.Timestamp;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.PersistenceCapable;

import dilmaj.shared.TermComposite;

@PersistenceCapable
public class Term {
    @Persistent
	String caption;
	
    @Persistent
	String username;
	
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    protected Long id;

    @Persistent
    protected Date timeStamp;

	public Term(TermComposite newTerm) {
		caption=newTerm.getCaption();
		timeStamp=newTerm.getTimeStamp();
		username=newTerm.getUser();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getCaption() {
		return caption;
	}
	
	public Date getTimestamp() {
		return timeStamp;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	public void setTimestamp(Date timeStamp) {
		this.timeStamp=timeStamp;
	}
}
