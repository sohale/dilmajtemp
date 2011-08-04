package dilmaj.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.shared.TermSuggestionComposite;

public interface TermSuggestionServiceAsync {

	void create(TermSuggestionComposite termSuggestion,
			AsyncCallback<TermSuggestionComposite> callback);

	void loadAll(AsyncCallback<List<TermSuggestionComposite>> callback);

	void update(TermSuggestionComposite tsVO,
			AsyncCallback<TermSuggestionComposite> callback);

	void load(long termSuggestionId,
			AsyncCallback<TermSuggestionComposite> callback);

}
