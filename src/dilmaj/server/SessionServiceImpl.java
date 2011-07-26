package dilmaj.server;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dilmaj.client.DilmajUserService;
import dilmaj.client.SessionService;
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

public class SessionServiceImpl extends RemoteServiceServlet implements
		SessionService {
	private static List<MessageComposite> allMessages=new ArrayList<MessageComposite>();
	private static HashMap<String, Date> allSessions=new HashMap<String, Date>();
	
	public static void addMessage(MessageComposite aMessage) {
		Date date=new Date();
		aMessage.setDateTime(date);
	}
	
	@Override
	public List<MessageComposite> getLog() {
		// TODO Auto-generated method stub
		List<MessageComposite> myMessages=new ArrayList<MessageComposite>();
		
		String sessionID=getThreadLocalRequest().getSession().getId();
		Date date=allSessions.get(sessionID);
		
		for (MessageComposite aMessage:allMessages) {
			if (aMessage.getDateTime().after(date)) {
				myMessages.add(aMessage);
			}
		}
		
		return myMessages;
	}

	@Override
	public String openSession() {
		// TODO Auto-generated method stub
		Date date=new Date();
		String sessionID=getThreadLocalRequest().getSession().getId();
		
		allSessions.put(sessionID, date);
		
		return sessionID;
	}

	@Override
	public void closeSession(String myID) {
		// TODO Auto-generated method stub
		allSessions.remove(myID);
	}
}
