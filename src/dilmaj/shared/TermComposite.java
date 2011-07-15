package dilmaj.shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

import dilmaj.client.domain.TermSuggestion;
import dilmaj.client.domain.Term;

public class TermComposite extends EntryComposite implements IsSerializable {
	String caption;
	int language=0;
	private Set<TermSuggestionComposite> suggestions=new HashSet<TermSuggestionComposite>();
	
	private static HashMap<Long, TermComposite> allTerms=new HashMap<Long, TermComposite>();
	
	public static TermComposite getInstance(Term term) {
		TermComposite termVO=allTerms.get(term.getId());
		if (termVO==null) {
			termVO=new TermComposite(term);
			allTerms.put(term.getId(), termVO);
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
		Integer theLanguage=term.getLanguage();
		if (theLanguage!=null)
			language=theLanguage;
	}
	
	public TermComposite(String caption) {
		this.caption=caption;
	}
	
	public TermComposite(String caption, String username) {
		this.caption=caption;
		this.username=username;
	}
	
	public TermComposite(String caption2, int language2) {
		// TODO Auto-generated constructor stub
		caption=caption2;
		language=language2;
	}

	public String getCaption() {
		return caption;
	}
	
	public void addSuggestion(TermSuggestionComposite suggestion) {
		suggestions.add(suggestion);
	}
	
	public Set<TermSuggestionComposite> getSuggestions() {
		return suggestions;
	}

	public int getLanguage() {
		// TODO Auto-generated method stub
		return language;
	}
	
	public void setLanguage(int newLanguage) {
		language=newLanguage;
	}
}
