package dilmaj.shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

import dilmaj.client.domain.TermSuggestion;
import dilmaj.client.domain.Term;

public class TermComposite extends EntryComposite implements IsSerializable {
	String caption;
	List<TermSuggestionComposite> suggestions=new ArrayList<TermSuggestionComposite>();
	
	private static HashMap<Long, TermComposite> allTerms=new HashMap<Long, TermComposite>();
	
	public static TermComposite getInstance(Term term) {
		TermComposite termVO=allTerms.get(term.getId());
		if (termVO==null) {
			termVO=new TermComposite(term);
			allTerms.put(termVO.getId(), termVO);
		}
		return termVO;
	}
	
	public TermComposite() {
		super();
	}
	
	private TermComposite(Term term) {
		this.caption=term.getCaption();
		this.id=term.getId();
		this.timeStamp=term.getTimestamp();
		this.username=term.getUsername();
	}
	
	public TermComposite(String caption) {
		this.caption=caption;
	}
	
	public TermComposite(String caption, String username) {
		this.caption=caption;
		this.username=username;
	}
	
	public String getCaption() {
		return caption;
	}
	
	public void addSuggestion(TermSuggestionComposite suggestion) {
		suggestions.add(suggestion);
	}
	
	public List<TermSuggestionComposite> getSuggestions() {
		return suggestions;
	}
}
