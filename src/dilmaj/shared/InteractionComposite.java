package dilmaj.shared;

public class InteractionComposite extends EntryComposite {
	TermSuggestionComposite tsVO;
	
	public InteractionComposite() {
		super();
	}
	
	public TermSuggestionComposite getTermSuggestion() {
		return tsVO;
	}
	
	public void setTermSuggestion(TermSuggestionComposite tsVO) {
		this.tsVO=tsVO;
	}
}
