package dilmaj.server;

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

import dilmaj.client.LikeService;
import dilmaj.client.TermService;
import dilmaj.client.domain.Interaction;
import dilmaj.client.domain.TermSuggestion;
import dilmaj.client.domain.Term;
import dilmaj.shared.LikeComposite;
import dilmaj.shared.TermComposite;
import dilmaj.shared.TermSuggestionComposite;

public class LikeServiceImpl extends RemoteServiceServlet implements LikeService {

	@Override
	public LikeComposite create(LikeComposite newLike) {
		// TODO Auto-generated method stub
		String username=(String)getThreadLocalRequest().getSession().getAttribute("loggedUser");
		newLike.setUser(username);
		
		Interaction like=new Interaction(newLike);
		
		TermSuggestionComposite tsVO=newLike.getTermSuggestion();
		like.setTermSuggestionID(tsVO.getId());
		
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(like);
            
    		String query = "select from " + TermSuggestion.class.getName()+" where id=="+tsVO.getId();

    	    List<TermSuggestion> allTermSuggestions = (List<TermSuggestion>) pm.newQuery(query).execute();
    		
    		if (allTermSuggestions==null)
    			return null;
    		
    		if (allTermSuggestions.size()!=1)
    			return null;
    		
    		TermSuggestion ts=allTermSuggestions.get(0);
    		ts.addLikeID(like.getId());
            newLike.setId(like.getId());
            pm.makePersistent(ts);
            //tsVO.a
        } finally {
            pm.close();
        }
                
		return newLike;
	}
}
