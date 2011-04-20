package dilmaj.server;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dilmaj.client.TermSuggestionService;
import dilmaj.client.domain.Interaction;
import dilmaj.client.domain.Term;
import dilmaj.client.domain.TermSuggestion;
import dilmaj.shared.InteractionComposite;
import dilmaj.shared.LikeComposite;
import dilmaj.shared.TermComposite;
import dilmaj.shared.TermSuggestionComposite;

public class TermSuggestionServiceImpl extends RemoteServiceServlet  implements TermSuggestionService {

	@Override
	public TermSuggestionComposite create(TermSuggestionComposite termSuggestion) {
		// TODO Auto-generated method stub
		String username=(String)getThreadLocalRequest().getSession().getAttribute("loggedUser");
		termSuggestion.setUser(username);
		
		TermSuggestion ts=new TermSuggestion(termSuggestion);

		//java.util.Date today = new java.util.Date();
	    //ts.setTimestamp(new Timestamp(today.getTime()));
		
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(ts);
        } finally {
            pm.close();
        }
        termSuggestion.setId(ts.getId());
        //termSuggestion.setTimestamp(ts.getTimestamp());
        
		return termSuggestion;
	}

	@Override
	public List<TermSuggestionComposite> loadAll() {
		// TODO Auto-generated method stub
		List<TermSuggestionComposite> tsVOs=new ArrayList<TermSuggestionComposite>();
		
		PersistenceManager pm=PMF.get().getPersistenceManager();

		String query = "select from " + TermSuggestion.class.getName();
	    List<TermSuggestion> allTermSuggestions = (List<TermSuggestion>) pm.newQuery(query).execute();
		
		if (allTermSuggestions!=null) {
			Iterator<TermSuggestion> iterator=allTermSuggestions.iterator();
			while (iterator.hasNext()) {
			    boolean termFound=false;
			    boolean suggestionFound=false;
			    TermComposite termVO=null, suggestionVO=null;
			    
				TermSuggestion ts=iterator.next();
				TermSuggestionComposite tsVO=new TermSuggestionComposite(ts);
				tsVOs.add(tsVO);

				Iterator<Long> interactionIDsIterator=ts.getInteractions().iterator();
				while (interactionIDsIterator.hasNext()) {
					Long interactionID=interactionIDsIterator.next();
					query = "select from " + Interaction.class.getName()+" where id=="+interactionID;
					List<Interaction> allInteractions=(List<Interaction>)pm.newQuery(query).execute();
					if (allInteractions!=null) {
						Iterator<Interaction> interactionsIterator=allInteractions.iterator();
						while (interactionsIterator.hasNext()) {
							Interaction interaction=interactionsIterator.next();
							if (interaction.getKind().equals("like")) {
								LikeComposite likeVO=new LikeComposite();
								tsVO.addInteraction(likeVO);
							}
						}
					}
				}
				
				if (!termFound) {
					query = "select from " + Term.class.getName()+" where id=="+ts.getTermId();
					List<Term> allTerms = (List<Term>) pm.newQuery(query).execute();
					if (allTerms!=null)
						if (allTerms.size()==1) {
							termVO=TermComposite.getInstance(allTerms.get(0));//new TermComposite(allTerms.get(0));
							tsVO.setTerm(termVO);
							termFound=true;
						}
				}
				
				if (!suggestionFound) {
					query = "select from " + Term.class.getName()+" where id=="+ts.getSuggestionId();
					List<Term> allTerms = (List<Term>) pm.newQuery(query).execute();
					if (allTerms!=null)
						if (allTerms.size()==1) {
							suggestionVO=TermComposite.getInstance(allTerms.get(0));//new TermComposite(allTerms.get(0));
							tsVO.setSuggestion(suggestionVO);
							suggestionFound=true;
						}
				}
				
				if (termFound && suggestionFound) {
					//termVO.addSuggestion(suggestionVO);
					termVO.addSuggestion(tsVO);
				}
			}
		}
		
		return tsVOs;
	}

	@Override
	public TermSuggestionComposite update(TermSuggestionComposite tsVO) {
		// TODO Auto-generated method stub
        PersistenceManager pm = PMF.get().getPersistenceManager();
        
		String query = "select from " + TermSuggestion.class.getName()+" where id=="+tsVO.getId();
		List<TermSuggestion> allTermSuggestions = (List<TermSuggestion>) pm.newQuery(query).execute();
		
		if (allTermSuggestions.size()!=1)
			return null;
		TermSuggestion ts=allTermSuggestions.get(0);
		//ts.setRank(tsVO.getRank());
		ts.setInteractions(tsVO.getInteractionIDs());
		
        try {
            pm.makePersistent(ts);
        } finally {
            pm.close();
        }
        //tsVO.increaseRank();
        
		return tsVO;
	}
}
