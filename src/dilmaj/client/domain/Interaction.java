package dilmaj.client.domain;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.PersistenceCapable;

import dilmaj.shared.LikeComposite;

@PersistenceCapable
public class Interaction {
    @Persistent
	Long tsID; // TermSuggestionID
	
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    protected Long id;

    @Persistent
    protected Date timeStamp;
	
    @Persistent
	String username;
	
    @Persistent
	String kind; //like (can be of different emotions), comment, sample

	public Interaction(LikeComposite newLike) {
		timeStamp=newLike.getTimeStamp();
		username=newLike.getUser();
		kind="like";
	}
	
	public String getKind() {
		return kind;
	}
	
	public Long getId() {
		return id;
	}
	
	public Date getTimestamp() {
		return timeStamp;
	}
	
	public void setTimestamp(Date timeStamp) {
		this.timeStamp=timeStamp;
	}
	
	public void setTermSuggestionID(Long tsID) {
		this.tsID=tsID;
	}
	
	public Long getTermSuggestionID() {
		return tsID;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
}
