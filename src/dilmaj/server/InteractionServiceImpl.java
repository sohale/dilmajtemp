package dilmaj.server;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dilmaj.client.InteractionService;
import dilmaj.client.LikeService;
import dilmaj.client.TermService;
import dilmaj.client.domain.Interaction;
import dilmaj.client.domain.TermSuggestion;
import dilmaj.client.domain.Term;
import dilmaj.client.domain.User;
import dilmaj.shared.CommentComposite;
import dilmaj.shared.InteractionComposite;
import dilmaj.shared.LikeComposite;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.TermComposite;
import dilmaj.shared.TermSuggestionComposite;
import dilmaj.shared.UseCaseComposite;

public class InteractionServiceImpl extends RemoteServiceServlet implements InteractionService {

	@Override
	public LikeComposite create(LikeComposite newLike) {
		// TODO Auto-generated method stub
		String username=(String)getThreadLocalRequest().getSession().getAttribute("loggedUser");
		if (username==null)
			return null;
		if (username.equals(""))
			return null;
		newLike.setUser(username);
		
		Interaction like=new Interaction(newLike);
	    //java.util.Date today = new java.util.Date();
	    //like.setTimestamp(new Timestamp(today.getTime()));
		
		TermSuggestionComposite tsVO=newLike.getTermSuggestion();
		
		Iterator<InteractionComposite> interactionIterator=tsVO.getInteractions().iterator();
		while (interactionIterator.hasNext()) {
			InteractionComposite interactionVO=interactionIterator.next();
			String interactionType=interactionVO.getClass().getName();
			if (interactionType.compareToIgnoreCase("dilmaj.shared.LikeComposite")==0) {
				String un=interactionVO.getUser();
				if (username.equals(un))
					return null;
			}
		}
		
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
    		ts.addInteractionID(like.getId());
            newLike.setId(like.getId());
            //newLike.setTimestamp(like.getTimestamp());
            pm.makePersistent(ts);
            //tsVO.a
        } finally {
            pm.close();
        }

        String fullURL=getThreadLocalRequest().getRequestURL().toString();
        String request=getThreadLocalRequest().getRequestURI();
        int index=fullURL.indexOf(request);
        String baseURL=fullURL.substring(0, index);
        String termURL=baseURL+"?termId="+tsVO.getTerm().getId();
        String suggestionURL=baseURL+"?termSuggestionId="+tsVO.getId();
        
        // sending notification email
        String tsOwner=tsVO.getSuggestion().getUser();
		String message=newLike.getUser()+" likes your suggestion <a href=\""+suggestionURL+"\">"+tsVO.getSuggestion().getCaption()+"</a> for the term  <a href=\""+termURL+"\">"+tsVO.getTerm().getCaption()+"</a>.";
		DilmajUserServiceImpl.sendMail(tsOwner, message);
		
		// live log
		//message=newComment.getUser()+" left a comment on "+tsOwner+"'s suggestion "+tsVO.getSuggestion().getCaption()+" for the term "+tsVO.getTerm().getCaption()+".";
		MessageComposite messageVO=new MessageComposite(message);
		//messageVO.setDateTime(new Date());
		SessionServiceImpl.addMessage(messageVO);

		return newLike;
	}

	@Override
	public CommentComposite create(CommentComposite newComment) {
		// TODO Auto-generated method stub
		String username=(String)getThreadLocalRequest().getSession().getAttribute("loggedUser");
		if (username==null)
			return null;
		if (username.equals(""))
			return null;
		newComment.setUser(username);
		
		Interaction Comment=new Interaction(newComment);
	    //java.util.Date today = new java.util.Date();
	    //Comment.setTimestamp(new Timestamp(today.getTime()));
		
		TermSuggestionComposite tsVO=newComment.getTermSuggestion();
		Comment.setTermSuggestionID(tsVO.getId());
		
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(Comment);
            
    		String query = "select from " + TermSuggestion.class.getName()+" where id=="+tsVO.getId();

    	    List<TermSuggestion> allTermSuggestions = (List<TermSuggestion>) pm.newQuery(query).execute();
    		
    		if (allTermSuggestions==null)
    			return null;
    		
    		if (allTermSuggestions.size()!=1)
    			return null;
    		
    		TermSuggestion ts=allTermSuggestions.get(0);
    		ts.addInteractionID(Comment.getId());
            newComment.setId(Comment.getId());
            //newComment.setTimestamp(Comment.getTimestamp());
            pm.makePersistent(ts);
            //tsVO.a
        } finally {
            pm.close();
        }
        
        String fullURL=getThreadLocalRequest().getRequestURL().toString();
        String request=getThreadLocalRequest().getRequestURI();
        int index=fullURL.indexOf(request);
        String baseURL=fullURL.substring(0, index);
        String termURL=baseURL+"?termId="+tsVO.getTerm().getId();
        String suggestionURL=baseURL+"?termSuggestionId="+tsVO.getId();
        
        // sending notification email
        String tsOwner=tsVO.getSuggestion().getUser();
		String message=newComment.getUser()+" left a comment on your suggestion <a href=\""+suggestionURL+"\">"+tsVO.getSuggestion().getCaption()+"</a> for the term  <a href=\""+termURL+"\">"+tsVO.getTerm().getCaption()+"</a>.";
		DilmajUserServiceImpl.sendMail(tsOwner, message);
		
		// live log
		message=newComment.getUser()+" left a comment on "+tsOwner+"'s suggestion "+tsVO.getSuggestion().getCaption()+" for the term "+tsVO.getTerm().getCaption()+".";
		MessageComposite messageVO=new MessageComposite(message);
		//messageVO.setDateTime(new Date());
		SessionServiceImpl.addMessage(messageVO);
    		
		return newComment;
	}

	@Override
	public UseCaseComposite create(UseCaseComposite newUseCase) {
		String username=(String)getThreadLocalRequest().getSession().getAttribute("loggedUser");
		if (username==null)
			return null;
		if (username.equals(""))
			return null;
		newUseCase.setUser(username);
		
		Interaction useCase=new Interaction(newUseCase);
	    //java.util.Date today = new java.util.Date();
	    //useCase.setTimestamp(new Timestamp(today.getTime()));
		
		TermSuggestionComposite tsVO=newUseCase.getTermSuggestion();
		useCase.setTermSuggestionID(tsVO.getId());
		
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(useCase);
            
    		String query = "select from " + TermSuggestion.class.getName()+" where id=="+tsVO.getId();

    	    List<TermSuggestion> allTermSuggestions = (List<TermSuggestion>) pm.newQuery(query).execute();
    		
    		if (allTermSuggestions==null)
    			return null;
    		
    		if (allTermSuggestions.size()!=1)
    			return null;
    		
    		TermSuggestion ts=allTermSuggestions.get(0);
    		ts.addInteractionID(useCase.getId());
            newUseCase.setId(useCase.getId());
            //newuseCase.setTimestamp(useCase.getTimestamp());
            pm.makePersistent(ts);
            //tsVO.a
        } finally {
            pm.close();
        }
        
        String fullURL=getThreadLocalRequest().getRequestURL().toString();
        String request=getThreadLocalRequest().getRequestURI();
        int index=fullURL.indexOf(request);
        String baseURL=fullURL.substring(0, index);
        String termURL=baseURL+"?termId="+tsVO.getTerm().getId();
        String suggestionURL=baseURL+"?termSuggestionId="+tsVO.getId();
        
        // sending notification email
        String tsOwner=tsVO.getSuggestion().getUser();
		String message=newUseCase.getUser()+" added a use case to your suggestion <a href=\""+suggestionURL+"\">"+tsVO.getSuggestion().getCaption()+"</a> for the term  <a href=\""+termURL+"\">"+tsVO.getTerm().getCaption()+"</a>.";
		DilmajUserServiceImpl.sendMail(tsOwner, message);
		
		// live log
		//message=newComment.getUser()+" left a comment on "+tsOwner+"'s suggestion "+tsVO.getSuggestion().getCaption()+" for the term "+tsVO.getTerm().getCaption()+".";
		MessageComposite messageVO=new MessageComposite(message);
		//messageVO.setDateTime(new Date());
		SessionServiceImpl.addMessage(messageVO);
    		
		return newUseCase;
	}
}
