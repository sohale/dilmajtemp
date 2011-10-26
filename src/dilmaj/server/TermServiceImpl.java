package dilmaj.server;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dilmaj.client.TermService;
import dilmaj.client.domain.Interaction;
import dilmaj.client.domain.TermSuggestion;
import dilmaj.client.domain.Term;
import dilmaj.shared.CommentComposite;
import dilmaj.shared.LikeComposite;
import dilmaj.shared.SearchResult;
import dilmaj.shared.TermComposite;
import dilmaj.shared.TermSuggestionComposite;
import dilmaj.shared.UseCaseComposite;
import dilmaj.shared.MyString;

public class TermServiceImpl extends RemoteServiceServlet implements TermService {
	@Override
	public TermComposite addEntry(TermComposite newTerm) {
		// TODO Auto-generated method stub
		String username=(String)getThreadLocalRequest().getSession().getAttribute("loggedUser");
		if (username==null)
			return null;
		if (username.equals(""))
			return null;
		newTerm.setUser(username);
		Term term=new Term(newTerm);

	    java.util.Date today = new java.util.Date();
	    term.setTimestamp(today);
		
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(term);
        } finally {
            pm.close();
        }
        newTerm.setId(term.getId());
        //newTerm.setTimestamp(term.getTimestamp());
        
		return newTerm;
	}

	@Override
	public HashMap<Long, TermComposite> loadAll() {
		// TODO Auto-generated method stub
		HashMap<Long, TermComposite> termVOs=new HashMap<Long, TermComposite>();
		List<TermSuggestionComposite> tsVOs=new ArrayList<TermSuggestionComposite>();
		
		PersistenceManager pm=PMF.get().getPersistenceManager();

		String query = "select from " + Term.class.getName();
	    List<Term> allTerms = (List<Term>) pm.newQuery(query).execute();
		
		if (allTerms!=null) {
			Iterator<Term> iterator=allTerms.iterator();
			while (iterator.hasNext()) {
				TermComposite termVO=TermComposite.getInstance(iterator.next());//new TermComposite(iterator.next());
				termVOs.put(termVO.getId(), termVO);
				termVO.getSuggestions().clear();
			}
		}
		
		query = "select from " + TermSuggestion.class.getName();
	    List<TermSuggestion> allTermSuggestions = (List<TermSuggestion>) pm.newQuery(query).execute();
		
		if (allTermSuggestions!=null) {
			Iterator<TermSuggestion> iterator=allTermSuggestions.iterator();
			
			while (iterator.hasNext()) {
				TermSuggestion ts=iterator.next();
				
				TermComposite tVO=termVOs.get(ts.getTermId());
				TermComposite sVO=termVOs.get(ts.getSuggestionId());
				
				StringBuilder runningTitle=null;
				if (tVO.getRunningTitle()!=null)
					runningTitle=new StringBuilder(tVO.getRunningTitle());
				else
					runningTitle=new StringBuilder("");

				runningTitle.append(" ");
				runningTitle.append(sVO.getCaption());
				tVO.setRunningTitle(runningTitle.toString());

				TermSuggestionComposite tsVO=new TermSuggestionComposite(ts);

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
								LikeComposite likeVO=new LikeComposite(interaction);
								tsVO.addInteraction(likeVO);
							}
							if (interaction.getKind().equals("comment")) {
								CommentComposite commentVO=new CommentComposite(interaction);
								tsVO.addInteraction(commentVO);
							}
							if (interaction.getKind().equals("useCase")) {
								UseCaseComposite sampleVO=new UseCaseComposite(interaction);
								tsVO.addInteraction(sampleVO);
							}
						}
					}
				}
				
				tsVO.setSuggestion(sVO);
				tsVO.setTerm(tVO);
				tVO.addSuggestion(tsVO);
				//tVO.setRunningTitle(runningTitle);
			}
		}
		
		return termVOs;
	}

	@Override
	public TermComposite get(TermComposite term) {
		// TODO Auto-generated method stub
		PersistenceManager pm=PMF.get().getPersistenceManager();
		
		String query = "select from " + Term.class.getName()+" where id=="+term.getId();

	    List<Term> allTerms = (List<Term>) pm.newQuery(query).execute();
		
		if (allTerms==null)
			return null;
		
		if (allTerms.size()!=1)
			return null;
		
		TermComposite termVO=TermComposite.getInstance(allTerms.get(0));
		termVO.getSuggestions().clear();
		
		query = "select from " + TermSuggestion.class.getName()+" where termID=="+termVO.getId();
	    List<TermSuggestion> allTermSuggestions = (List<TermSuggestion>) pm.newQuery(query).execute();
		
		if (allTermSuggestions!=null) {
			Iterator<TermSuggestion> iterator=allTermSuggestions.iterator();
			while (iterator.hasNext()) {
				TermSuggestion ts=iterator.next();
				
				//TermComposite tVO=termVOs.get(ts.getTermId());
				query = "select from " + Term.class.getName()+" where id=="+ts.getSuggestionId();
			    List<Term> allSuggestions = (List<Term>) pm.newQuery(query).execute();
				if (allSuggestions==null)
					return null;
				if (allSuggestions.size()!=1)
					return null;
				TermComposite suggestionVO=TermComposite.getInstance(allSuggestions.get(0));

				TermSuggestionComposite tsVO=new TermSuggestionComposite(ts);

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
								LikeComposite likeVO=new LikeComposite(interaction);
								tsVO.addInteraction(likeVO);
							}
							if (interaction.getKind().equals("comment")) {
								CommentComposite commentVO=new CommentComposite(interaction);
								tsVO.addInteraction(commentVO);
							}
							if (interaction.getKind().equals("useCase")) {
								UseCaseComposite sampleVO=new UseCaseComposite(interaction);
								tsVO.addInteraction(sampleVO);
							}
						}
					}
				}
				
				tsVO.setSuggestion(suggestionVO);
				tsVO.setTerm(termVO);
				termVO.addSuggestion(tsVO);
			}
		}

		return termVO;//new TermComposite(allTerms.get(0));
	}

	@Override
	public List<SearchResult> getSome(String capionFilter) {
		// TODO Auto-generated method stub
		PersistenceManager pm=PMF.get().getPersistenceManager();
		
		String query = "select from " + Term.class.getName();

	    List<Term> allTerms = (List<Term>) pm.newQuery(query).execute();
	    
	    List<SearchResult> allTermVOs=new ArrayList<SearchResult>();
	    Iterator<Term> iterator=allTerms.iterator();
	    while (iterator.hasNext()) {
	    	Term term=iterator.next();
	    	if (term!=null) {
	    		if (term.getCaption()!=null) {
	    			
	    			//int distance=MyString.getInstance().getDistance(term.getCaption(), capionFilter);
	    			
	    			//if (term.getCaption().length()>distance)
				    	if (/*distance<2*/term.getCaption().contains(capionFilter)) {
					    	TermComposite termVO=TermComposite.getInstance(term);
					    	SearchResult searchResult=new SearchResult(termVO, 0/*distance*/);
					    	allTermVOs.add(searchResult);
				    	}
	    		}
	    	}
	    }
	    
	    return allTermVOs;
	}

	@Override
	public HashMap<Long, TermComposite> getMyTerms() {
		// TODO Auto-generated method stub
		HashMap<Long, TermComposite> termsMap=new HashMap<Long, TermComposite>();
		String username=(String)getThreadLocalRequest().getSession().getAttribute("loggedUser");
		
		PersistenceManager pm=PMF.get().getPersistenceManager();
		
		String query = "select from " + Term.class.getName();// + " where username=='"+username+"'";

	    List<Term> allTerms = (List<Term>) pm.newQuery(query).execute();
	    
	    Iterator<Term> iterator=allTerms.iterator();
	    while (iterator.hasNext()) {
	    	Term term=iterator.next();
	    	if (term.getUsername()!=null) {
		    	if (term.getUsername().equalsIgnoreCase(username)) {
			    	TermComposite termVO=TermComposite.getInstance(term);
			    	termsMap.put(termVO.getId(), termVO);
		    	}
	    	}
	    }
	    	
		return termsMap;
	}

	@Override
	public List<TermComposite> getTermsWithSuggestion(int from, int to) {
		// TODO Auto-generated method stub
		List<TermComposite> termVOs=new ArrayList<TermComposite>();
		List<TermSuggestionComposite> tsVOs=new ArrayList<TermSuggestionComposite>();
		
		PersistenceManager pm=PMF.get().getPersistenceManager();

		String query = "select from " + TermSuggestion.class.getName() + " order by termID";
		List<TermSuggestion> tsList = (List<TermSuggestion>) pm.newQuery(query).execute();
		
		Iterator<TermSuggestion> tsIterator=tsList.iterator();
		Long termId=null;
		TermComposite termVO=null;
		int j=-1;
		boolean addNow=false;
		StringBuilder runningTitle=null;
		int next=0;
		int prev=0;
		while (tsIterator.hasNext()) {
			TermSuggestion ts=tsIterator.next();
			TermSuggestionComposite tsVO=new TermSuggestionComposite(ts);
			
			Object idInstance = pm.newObjectIdInstance(Term.class, ts.getTermId());
		    Term term=(Term)pm.getObjectById(idInstance);//tIterator.next();
		    
		    if (termId==null || !termId.equals(term.getId())) {
			    termVO=TermComposite.getInstance(term);
			    termId=termVO.getId();
			    runningTitle=new StringBuilder("");
			    ++j;
			    if (j<from) {
			    	addNow=false;
			    	++prev;
			    } else if (j<=to) {
			    	addNow=true;
				    termVOs.add(termVO);
			    } else {
			    	next++;
			    }
		    }
			
		    if (addNow) {
				idInstance = pm.newObjectIdInstance(Term.class, ts.getSuggestionId());
			    Term suggestion=(Term)pm.getObjectById(idInstance);//tIterator.next();
			    TermComposite sVO=TermComposite.getInstance(suggestion);
			    
				runningTitle.append("-");
				runningTitle.append(sVO.getCaption());

				/*Iterator<Long> interactionIDsIterator=ts.getInteractions().iterator();
				while (interactionIDsIterator.hasNext()) {
					Long interactionID=interactionIDsIterator.next();
					query = "select from " + Interaction.class.getName()+" where id=="+interactionID;
					List<Interaction> allInteractions=(List<Interaction>)pm.newQuery(query).execute();
					if (allInteractions!=null) {
						Iterator<Interaction> interactionsIterator=allInteractions.iterator();
						while (interactionsIterator.hasNext()) {
							Interaction interaction=interactionsIterator.next();
							if (interaction.getKind().equals("like")) {
								LikeComposite likeVO=new LikeComposite(interaction);
								tsVO.addInteraction(likeVO);
							}
							if (interaction.getKind().equals("comment")) {
								CommentComposite commentVO=new CommentComposite(interaction);
								tsVO.addInteraction(commentVO);
							}
							if (interaction.getKind().equals("useCase")) {
								UseCaseComposite sampleVO=new UseCaseComposite(interaction);
								tsVO.addInteraction(sampleVO);
							}
						}
					}
				}
			    
				tsVO.setSuggestion(sVO);
				tsVO.setTerm(termVO);
				termVO.addSuggestion(tsVO);*/
				termVO.setRunningTitle(runningTitle.toString());
		    }
		}
		
		TermComposite nextTerms=new TermComposite(); // this is an information element only, which says how many more terms are left
		nextTerms.setId(new Long(next));
		termVOs.add(nextTerms);
		TermComposite prevTerms=new TermComposite(); // this is an information element only, which says how many more terms are left
		prevTerms.setId(new Long(prev));
		termVOs.add(prevTerms);
		return termVOs;
	}
	
	@Override
	public List<TermComposite> getTermsWithoutSuggestion(int from, int to) {
		// TODO Auto-generated method stub
		List<TermComposite> termVOs=new ArrayList<TermComposite>();
		
		PersistenceManager pm=PMF.get().getPersistenceManager();

		String query = "select from " + Term.class.getName() + " order by caption";
		List<Term> termsList = (List<Term>) pm.newQuery(query).execute();
		
		int next=0;
		int prev=0;
		if (termsList!=null) {
			Iterator<Term> iterator=termsList.iterator();
			int j=-1;
			while (iterator.hasNext()) {
				Term term=iterator.next();
				if (j<to) {
					query = "select from " + TermSuggestion.class.getName() + " where termID=="+term.getId();
					List tsList = (List)pm.newQuery(query).execute();
					if (tsList==null || tsList.size()==0) {
						++j;
						if (j>=from)
							termVOs.add(TermComposite.getInstance(term));
						else
							++prev;
					}
				} else {
					++next;
				}
			}
		}
			
		TermComposite nextTerms=new TermComposite(); // this is an information element only, which says how many more terms are left
		nextTerms.setId(new Long(next));
		termVOs.add(nextTerms);
		TermComposite prevTerms=new TermComposite(); // this is an information element only, which says how many more terms are left
		prevTerms.setId(new Long(prev));
		termVOs.add(prevTerms);
		return termVOs;
	}

	@Override
	public List<TermComposite> getMyTerms(int from, int to) {
		// TODO Auto-generated method stub
		List<TermComposite> termVOs=new ArrayList<TermComposite>();
		String username=(String)getThreadLocalRequest().getSession().getAttribute("loggedUser");
		
		PersistenceManager pm=PMF.get().getPersistenceManager();
		
		String query = "select from " + Term.class.getName();// + " where username=='"+username+"'";

	    List<Term> allTerms = (List<Term>) pm.newQuery(query).execute();
	    
	    Iterator<Term> iterator=allTerms.iterator();
	    int j=-1;
	    int next=0;
	    int prev=0;
	    while (iterator.hasNext()) {
	    	Term term=iterator.next();
	    	if (term.getUsername()!=null) {
		    	if (term.getUsername().equalsIgnoreCase(username)) {
		    		if (j<to) {
				    	TermComposite termVO=TermComposite.getInstance(term);
				    	++j;
				    	if (j>=from)
				    		termVOs.add(termVO);
				    	else
				    		++prev;
		    		} else {
		    			++next;
		    		}
		    	}
	    	}
	    }
	    	
		TermComposite nextTerms=new TermComposite(); // this is an information element only, which says how many more terms are left
		nextTerms.setId(new Long(next));
		termVOs.add(nextTerms);
		TermComposite prevTerms=new TermComposite(); // this is an information element only, which says how many more terms are left
		prevTerms.setId(new Long(prev));
		termVOs.add(prevTerms);
		return termVOs;
	}

	@Override
	public TermComposite getTermDetails(TermComposite term) {
		// TODO Auto-generated method stub
		PersistenceManager pm=PMF.get().getPersistenceManager();
		
		String query = "select from " + Term.class.getName()+" where id=="+term.getId();

	    List<Term> allTerms = (List<Term>) pm.newQuery(query).execute();
		
		if (allTerms==null)
			return null;
		
		if (allTerms.size()!=1)
			return null;
		
		TermComposite termVO=TermComposite.getInstance(allTerms.get(0));
		termVO.getSuggestions().clear();
		
		query = "select from " + TermSuggestion.class.getName()+" where termID=="+termVO.getId();
	    List<TermSuggestion> allTermSuggestions = (List<TermSuggestion>) pm.newQuery(query).execute();
		
		if (allTermSuggestions!=null) {
			Iterator<TermSuggestion> iterator=allTermSuggestions.iterator();
			while (iterator.hasNext()) {
				TermSuggestion ts=iterator.next();
				
				//TermComposite tVO=termVOs.get(ts.getTermId());
				query = "select from " + Term.class.getName()+" where id=="+ts.getSuggestionId();
			    List<Term> allSuggestions = (List<Term>) pm.newQuery(query).execute();
				if (allSuggestions==null)
					return null;
				if (allSuggestions.size()!=1)
					return null;
				TermComposite suggestionVO=TermComposite.getInstance(allSuggestions.get(0));

				TermSuggestionComposite tsVO=new TermSuggestionComposite(ts);

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
								LikeComposite likeVO=new LikeComposite(interaction);
								tsVO.addInteraction(likeVO);
							}
							if (interaction.getKind().equals("comment")) {
								CommentComposite commentVO=new CommentComposite(interaction);
								tsVO.addInteraction(commentVO);
							}
							if (interaction.getKind().equals("useCase")) {
								UseCaseComposite sampleVO=new UseCaseComposite(interaction);
								tsVO.addInteraction(sampleVO);
							}
						}
					}
				}
				
				tsVO.setSuggestion(suggestionVO);
				tsVO.setTerm(termVO);
				termVO.addSuggestion(tsVO);
			}
		}

		return termVO;//new TermComposite(allTerms.get(0));
	}
}
 