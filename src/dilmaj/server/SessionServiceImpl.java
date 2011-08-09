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
	private static long timer=0;
	private static List<MessageComposite> allMessages=new ArrayList<MessageComposite>();
	private static HashMap<String, Long> allSessions=new HashMap<String, Long>();
	
	public static void addMessage(MessageComposite aMessage) {
		aMessage.setSequence(timer++);
		allMessages.add(aMessage);
	}
	
	@Override
	public List<MessageComposite> getLog() {
		// TODO Auto-generated method stub
		List<MessageComposite> myMessages=new ArrayList<MessageComposite>();
		
		if (allSessions.size()==0)
			return myMessages;
		
		String sessionID=getThreadLocalRequest().getSession().getId();
		
		if (sessionID==null || allSessions==null)
			return myMessages;
		
		if (allSessions.get(sessionID)==null)
			return myMessages;
		
		long seq=allSessions.get(sessionID);
		long newSeq=seq;
		
		for (MessageComposite aMessage:allMessages) {
			if (aMessage.getSequence() > seq) {
				myMessages.add(aMessage);
				newSeq=aMessage.getSequence();
			}
		}
		
		if (sessionID!=null)
			allSessions.put(sessionID, newSeq);
		
		return myMessages;
	}

	@Override
	public String openSession() {
		// TODO Auto-generated method stub
		Date date=new Date();
		String sessionID=getThreadLocalRequest().getSession().getId();
		
		allSessions.put(sessionID, timer);
		
		return sessionID;
	}

	@Override
	public void closeSession(String myID) {
		// TODO Auto-generated method stub
		allSessions.remove(myID);
	}
}
