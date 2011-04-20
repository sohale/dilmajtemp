package dilmaj.client.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.PersistenceCapable;

import dilmaj.shared.TermSuggestionComposite;

@PersistenceCapable
public class TermSuggestion {
    @Persistent
	String caption;
	
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    protected Long id;

    @Persistent
    protected Date timeStamp;

    @Persistent
    protected Long termID;

    @Persistent
    protected Long suggestionID;

    @Persistent
    protected Long rank=new Long(0);

    @Persistent
    protected List<Long> interactionIDs=new ArrayList<Long>();

	@Persistent
	String username;
    
	public TermSuggestion(TermSuggestionComposite newSuggestion) {
		termID=newSuggestion.getTerm().getId();
		suggestionID=newSuggestion.getSuggestion().getId();
		timeStamp=newSuggestion.getTimeStamp();
		rank=newSuggestion.getRank();
		username=newSuggestion.getUser();
		interactionIDs=newSuggestion.getInteractionIDs();
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
	
	public Long getTermId() {
		return termID;
	}
	
	public Long getSuggestionId() {
		return suggestionID;
	}
	
	public Long getRank() {
		return rank;
	}
	
	public void addLikeID(Long likeID) {
		if (interactionIDs==null)
			interactionIDs=new ArrayList<Long>();
		
		Iterator<Long> likeIterator=interactionIDs.iterator();
		while (likeIterator.hasNext()) {
			Long anID=likeIterator.next();
			if (anID!=null)
				if (anID.longValue()==likeID.longValue())
					return;
		}
		
		interactionIDs.add(new Long(likeID.longValue()));
	}
	
	public void increaseRank() {
		rank++;
	}

	public void decreaseRank() {
		if (rank>0)
			rank--;
	}

	public void setRank(Long newRank) {
		this.rank=newRank;
	}
	
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	
	public void setInteractions(List<Long> interactions) {
		interactionIDs=interactions;
	}
	
	public List<Long> getInteractions() {
		return interactionIDs;
	}

	public void setTimestamp(Date timeStamp) {
		this.timeStamp=timeStamp;
	}
}
