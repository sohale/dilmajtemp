package dilmaj.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dilmaj.shared.TermSuggestionComposite;

@RemoteServiceRelativePath("termsuggestionservice")
public interface TermSuggestionService extends RemoteService {
	public TermSuggestionComposite create(TermSuggestionComposite termSuggestion);
	public List<TermSuggestionComposite> loadAll();
	public TermSuggestionComposite update(TermSuggestionComposite tsVO);
}
