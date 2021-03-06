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
import java.net.ServerSocket;
import java.security.SecureRandom;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

public class SessionServiceImpl extends RemoteServiceServlet implements
		SessionService {
	private static int timer=0;
	private static List<MessageComposite> allMessages=new ArrayList<MessageComposite>();
	//private static HashMap<String, Integer> allSessions=new HashMap<String, Integer>();
	
	public static void addMessage(MessageComposite aMessage) {
		allMessages.add(aMessage);
		aMessage.setSequence(timer++);
	}
	
	@Override
	public List<MessageComposite> getLog(int lastId) {
		// TODO Auto-generated method stub
		//String sessionID=getThreadLocalRequest().getSession().getId();
		//SessionThread sessionThread=allSessions.get(sessionID);
		//sessionThread.setLastId(lastId);
		
		List<MessageComposite> myMessages=new ArrayList<MessageComposite>();
		
		/*if (allSessions.size()==0)
			return myMessages;*/
		
		String sessionID=getThreadLocalRequest().getSession().getId();
		
		/*if (sessionID==null || allSessions==null)
			return myMessages;*/
		
		/*if (allSessions.get(sessionID)==null)
			return myMessages;*/
		
		/*int seq=allSessions.get(sessionID);
		int newSeq=seq;*/

		for (MessageComposite aMessage:allMessages) {
			int seq=aMessage.getSequence();
			if (seq>lastId)
				myMessages.add(aMessage);
		}
		
		/*for (int i=lastId+1;i<allMessages.size();i++) {
			MessageComposite aMessage=allMessages.get(i);
			myMessages.add(aMessage);
		}*/
		
		/*if (sessionID!=null)
			allSessions.put(sessionID, timer);*/
		
		/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		return myMessages;
	}

	@Override
	public Integer openSession() {
		// TODO Auto-generated method stub
		//String sessionID=getThreadLocalRequest().getSession().getId();
		
		int index=timer;
		//allSessions.put(sessionID, index);
		MessageComposite newMessage=new MessageComposite("A new guest connected!!");
		addMessage(newMessage);
		//allSessions.put(sessionID, timer);
		
		return index;
	}

	@Override
	public void closeSession(String myID) {
		//allSessions.remove(myID);
	}
	
	/*private Thread makeThread() {
		Runnable runLoop=new Runnable() {
			
			@Override
			public void run() {
				try {
					synchronized(allMessages) {
						
					}
				} catch (InterruptedException e) {
					return;
				}
			}
			
		};
		
		return new SessionThread(runLoop);
	}*/
}
