package dilmaj.server;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dilmaj.client.DilmajUserService;
import dilmaj.client.domain.Settings;
import dilmaj.client.domain.Term;
import dilmaj.client.domain.User;
import dilmaj.shared.EmailComposite;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.SettingsComposite;
import dilmaj.shared.TermComposite;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

public class DilmajUserServiceImpl extends RemoteServiceServlet implements
		DilmajUserService {

	@Override
	public MemberComposite create(MemberComposite userVO) {
		// TODO Auto-generated method stub
        SecureRandom random = new SecureRandom();
        String activator=new BigInteger(130, random).toString(32);
		
		User user=new User(userVO);
		user.setActivator("permanent");//activator);
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(user);
        } finally {
            pm.close();
        }
        userVO.setId(user.getId());
        userVO.setActivator(activator);
        
		return userVO;
	}

	@Override
	public MemberComposite find(MemberComposite userVO) {
		PersistenceManager pm=PMF.get().getPersistenceManager();
		MemberComposite member=null;
		
		//google's jQL?
	    String query = "select from " + User.class.getName()+" where username=='"+userVO.getUsername()+"'";//+" and activator=="+userVO.getActivator();

	    List<User> allUsers = (List<User>) pm.newQuery(query).execute();
	
	    if (allUsers==null)
	    	return null;
	
	    User user=allUsers.get(0); //Get the first entry
	    String activator=user.getActivator(); //Has user activated?

	    member=MemberComposite.getInstance(user); //copies all fields
	    
	    // exceptions for activation: activate all.
	    if (activator.compareToIgnoreCase("activator")!=0) {
		    user.setActivator("permanent");
		    
		    try {
		    	pm.makePersistent(user); //do the "update" on table
		    } finally {
	            pm.close();
	        }
		    
		    member.setActivator("permanent");
	    }
    	getThreadLocalRequest().getSession().setAttribute("loggedUser", member.getUsername());
		
	    //set the session variable
	    
	    return member;
	}

	@Override
	public MessageComposite sendMail(EmailComposite emailVO) {
		// TODO Auto-generated method stub
		
    	Properties props = new Properties();
    	props.put("mail.smtp.host", "smtp.gmail.com");
    	props.put("mail.smtp.socketFactory.port", "465");
	props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
    	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.port", "465");
 
	Session session = Session.getDefaultInstance(props,
	
			new javax.mail.Authenticator() 
	{
		protected PasswordAuthentication getPasswordAuthentication()
		{ return new PasswordAuthentication("ali.fatolahi@gmail.com","Google_3m9a8r6y8a8m7");	}
	});		
 
    	try {
 
            Message message = new MimeMessage(session);
	    message.setFrom(new InternetAddress("ali.fatolahi@gmail.com"));
	    message.setRecipients(Message.RecipientType.TO, 
                        InternetAddress.parse(emailVO.getTo()));
	    message.setSubject(emailVO.getSubject());
	    message.setText(emailVO.getBody());
 
	    Transport.send(message);
 
	    System.out.println("Done");
	    return new MessageComposite("Success");
 
    	} catch (MessagingException e) {
    	    throw new RuntimeException(e);
    	}
    }

	@Override
	public MemberComposite update(MemberComposite userVO) {
		// TODO Auto-generated method stub
        PersistenceManager pm = PMF.get().getPersistenceManager();
		User user=new User(userVO);
		user.setId(userVO.getId());
        try {
            pm.makePersistent(user);
        } finally {
            pm.close();
        }
        return userVO;
	}

	@Override
	public MemberComposite getLoggedUser() {
		// TODO Auto-generated method stub
		PersistenceManager pm=PMF.get().getPersistenceManager();
		MemberComposite member=null;

		String username=(String)getThreadLocalRequest().getSession().getAttribute("loggedUser");

	    String query = "select from " + User.class.getName()+" where username=='"+username+"'";//+" and activator=="+userVO.getActivator();

	    List<User> allUsers = (List<User>) pm.newQuery(query).execute();
	
	    if (allUsers==null)
	    	return null;
	    
	    if (allUsers.size()<1)
	    	return null;
	
	    User user=allUsers.get(0); //Get the first entry
	    String activator=user.getActivator(); //Has user activated?

	    member=MemberComposite.getInstance(user); //copies all fields
	    
	    // exceptions for activation: activate all.
	    if (activator.compareToIgnoreCase("activator")!=0) {
		    user.setActivator("permanent");
		    
		    try {
		    	pm.makePersistent(user); //do the "update" on table
		    } finally {
	            pm.close();
	        }
		    
		    member.setActivator("permanent");
	    }
    	getThreadLocalRequest().getSession().setAttribute("loggedUser", member.getUsername());
		
	    //set the session variable
	    
	    return member;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		getThreadLocalRequest().getSession().setAttribute("loggedUser", null);
	}
	
}
