package dilmaj.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SearchResult implements IsSerializable {
	TermComposite theTerm;
	int distance;
	
	public SearchResult() {
	
	}
	
	public SearchResult(TermComposite term, int distance) {
		theTerm=term;
		this.distance=distance;
	}
	
	public TermComposite getTermComposite() {
		return theTerm;
	}
	
	public int getDistance() {
		return distance;
	}
}
