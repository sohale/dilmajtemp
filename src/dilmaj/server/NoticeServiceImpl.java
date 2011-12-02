package dilmaj.server;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
/*import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;*/

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
/*import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.XMLParser;*/

import dilmaj.client.FeedService;
import dilmaj.client.domain.Term;
import dilmaj.shared.TermSuggestionComposite;

public class NoticeServiceImpl extends RemoteServiceServlet implements FeedService {

	@Override
	public String getFeed(Date beginDate) {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document feedDocument = docBuilder.newDocument();

		Element rss=feedDocument.createElement("rss");
		rss.setAttribute("xmlns:content", "http://purl.org/rss/1.0/modules/content/");
		rss.setAttribute("xmlns:wfw", "http://wellformedweb.org/CommentAPI/");
		rss.setAttribute("xmlns:dc", "http://purl.org/dc/elements/1.1/");
		rss.setAttribute("xmlns:atom", "http://www.w3.org/2005/Atom");
		rss.setAttribute("xmlns:sy", "http://purl.org/rss/1.0/modules/syndication/");
		rss.setAttribute("xmlns:slash", "http://purl.org/rss/1.0/modules/slash/");
		rss.setAttribute("version", "2.0");
		feedDocument.appendChild(rss);

		Element channel=feedDocument.createElement("channel");
		Element channelTitle=feedDocument.createElement("title");
		//channelTitle.s.setTextContent("Dilmaj");
		channel.appendChild(channelTitle);
		Element linkTitle=feedDocument.createElement("atom:link");
		linkTitle.setAttribute("href", "http://dilmajtest.appspot.com/testgwt/feed");
		linkTitle.setAttribute("rel", "self");
		linkTitle.setAttribute("type", "application/rss+xml");
		channel.appendChild(linkTitle);
		Element link=feedDocument.createElement("link");
		//link.setTextContent("http://dilmajtest.appspot.com");
		channel.appendChild(link);
		rss.appendChild(channel);

		List<TermSuggestionComposite> tsVOs=new ArrayList<TermSuggestionComposite>();

		PersistenceManager pm=PMF.get().getPersistenceManager();

		String query = "select from " + Term.class.getName();
		List<Term> allTerms = (List<Term>) pm.newQuery(query).execute();
		//PrintWriter out = resp.getWriter();    
		if (allTerms!=null) {
			Iterator<Term> iterator=allTerms.iterator();
			while (iterator.hasNext()) {
				Term term=iterator.next();

				if (beginDate==null) {				
					if (term.getTimestamp()!=null) {
						Element termElement=feedDocument.createElement("item");
						channel.appendChild(termElement);

						Element title=feedDocument.createElement("title");
						//title.setTextContent(term.getCaption());
						termElement.appendChild(title);

						Element pubDate=feedDocument.createElement("pubDate");
						//pubDate.setTextContent(formatDate(term.getTimestamp()));
						termElement.appendChild(pubDate);

						Element guid=feedDocument.createElement("guid");
						guid.setAttribute("isPermaLink", "false");
						//String fullURL=req.getRequestURL().toString();
						//String request=req.getRequestURI();
						//int index=fullURL.indexOf(request);
						//String baseURL=fullURL.substring(0, index);
						//String termURL=baseURL+"?termId="+term.getId();
						//guid.setTextContent(termURL);
						termElement.appendChild(guid);
					}
				} else {
					if (term.getTimestamp()!=null) {
						if (term.getTimestamp().after(beginDate)) {
							Element termElement=feedDocument.createElement("item");
							channel.appendChild(termElement);

							Element title=feedDocument.createElement("title");
							//title.setTextContent(term.getCaption());
							termElement.appendChild(title);

							Element pubDate=feedDocument.createElement("pubDate");
							//pubDate.setTextContent(formatDate(term.getTimestamp()));
							termElement.appendChild(pubDate);

							Element guid=feedDocument.createElement("guid");
							guid.setAttribute("isPermaLink", "false");
							//String fullURL=req.getRequestURL().toString();
							//String request=req.getRequestURI();
							//int index=fullURL.indexOf(request);
							//String baseURL=fullURL.substring(0, index);
							//String termURL=baseURL+"?termId="+term.getId();
							//guid.setTextContent(termURL);
							termElement.appendChild(guid);
						}
					}
				}
			}
		}

		//return feedDocument;
		//return feedDocument.toString();
		System.setProperty("javax.xml.transform.TransformerFactory", 
		"com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl"); 
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(feedDocument);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			transformer.transform(source, result);
			
			return writer.toString();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//out.flush();
		return null;
	}
}
