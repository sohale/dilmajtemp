package dilmaj.shared;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

import dilmaj.client.domain.TermSuggestion;

public class TermSuggestionComposite extends EntryComposite implements IsSerializable {
	TermComposite term;
	TermComposite suggestion;
	long rank=0;
	Set<InteractionComposite> interactions=new HashSet<InteractionComposite>();
	
	public TermSuggestionComposite() {
		super();
	}
	
	public TermSuggestionComposite(TermSuggestion termSuggestion) {
		this.id=termSuggestion.getId();
		if (termSuggestion.getTimestamp()==null)
			timeStamp=new Date();
		else
			this.timeStamp=termSuggestion.getTimestamp();
		if (termSuggestion.getRank()!=null)
			this.rank=termSuggestion.getRank();
		else
			this.rank=0;
		this.username=termSuggestion.getUsername();
	}
	
	public TermSuggestionComposite(TermComposite term, TermComposite suggestion) {
		this.term=term;
		this.suggestion=suggestion;
	}
	
	public TermComposite getSuggestion() {
		return suggestion;
	}
	
	public TermComposite getTerm() {
		return term;
	}
	
	public void setTerm(TermComposite termVO) {
		term=termVO;
	}
	
	public void setSuggestion(TermComposite suggestionVO) {
		suggestion=suggestionVO;
	}
	
	public long getRank() {
		return rank;
	}
	
	public void addInteraction(InteractionComposite interactionVO) {
		interactions.add(interactionVO);
	}

	public void increaseRank() {
		rank++;
	}

	public void decreaseRank() {
		if (rank>0)
			rank--;
	}

	public List<Long> getInteractionIDs() {
		// TODO Auto-generated method stub
		List<Long> interactionIDs=new ArrayList<Long>();
		
		Iterator<InteractionComposite> iterator=interactions.iterator();
		while (iterator.hasNext()) {
			InteractionComposite interactionComposite=iterator.next();
			interactionIDs.add(interactionComposite.getId());
		}
		
		return interactionIDs;
	}
	
	public int getLikes() {
		int nLikes=0;
		
		Iterator<InteractionComposite> iterator=interactions.iterator();
		while (iterator.hasNext()) {
			InteractionComposite interactionComposite=iterator.next();
			
			if (interactionComposite.getClass().equals(LikeComposite.class)) {
				LikeComposite likeVO=(LikeComposite)interactionComposite;
				nLikes++;
			}
		}
		
		return nLikes;
	}
	
	public Set<InteractionComposite> getInteractions() {
		return interactions;
	}
}
