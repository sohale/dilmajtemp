package dilmaj.client;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dilmaj.shared.TermComposite;

@RemoteServiceRelativePath("termservice")
public interface TermService extends RemoteService {
	public TermComposite addEntry(TermComposite newTerm);
	public HashMap<Long, TermComposite> loadAll();
	public TermComposite get(TermComposite term);
	public List<TermComposite> getSome(String capionFilter);
	public HashMap<Long, TermComposite> getMyTerms();
}
