package dilmaj.client;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.shared.SearchResult;
import dilmaj.shared.TermComposite;

public interface TermServiceAsync {

	void addEntry(TermComposite newTerm, AsyncCallback<TermComposite> callback);

	void loadAll(AsyncCallback<HashMap<Long, TermComposite>> callback);

	void get(TermComposite term, AsyncCallback<TermComposite> callback);

	void getSome(String capionFilter,
			AsyncCallback<List<SearchResult>> callback);

	void getMyTerms(AsyncCallback<HashMap<Long, TermComposite>> callback);
}
