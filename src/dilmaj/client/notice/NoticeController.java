package dilmaj.client.notice;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

/*import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;*/

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;

import dilmaj.client.DilmajUserService;
import dilmaj.client.DilmajUserServiceAsync;
import dilmaj.client.FeedService;
import dilmaj.client.FeedServiceAsync;
import dilmaj.shared.Controller;

public class NoticeController extends Controller {
	private FeedServiceAsync feedSvc = GWT.create(FeedService.class);
	Date date=new Date();
	
	public NoticeController() {
		//readFeed();
		feedSvc.getFeed(date, new GetFeedCallback());
	}
	
	public void readFeed() {
        //Document feedDom = XMLParser.parse();
        //Element element=feedDom.getDocumentElement();
	}
	
	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		//readFeed();
		//feedSvc.getFeed(date, new GetFeedCallback());
		Image image=(Image)event.getSource();
		if (image.getAltText().equals("nonotice")) {
			image.setUrl("images/notice.jpg");
			image.setAltText("notice");
		} else {
			image.setUrl("images/nonotice.jpg");
			image.setAltText("nonotice");
		}
	}
}
